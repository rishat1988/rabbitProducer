package com.example.rabbitproducer.services.impl

import com.example.rabbitproducer.dtos.SubscriptionTariffDto
import com.example.rabbitproducer.entities.ActivationCode
import com.example.rabbitproducer.entities.Subscription
import com.example.rabbitproducer.repositories.SubscriptionRepository
import com.example.rabbitproducer.repositories.TariffRepository
import com.example.rabbitproducer.services.SubscriptionService
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
class SubscriptionServiceImpl(
    private val subscriptionRepository: SubscriptionRepository,
    private val tariffRepository: TariffRepository,
    private val streamBridge: StreamBridge
) : SubscriptionService {

    private val log = LoggerFactory.getLogger(SubscriptionServiceImpl::class.java)

    @Transactional(rollbackFor = [java.lang.Exception::class])
    @Throws(java.lang.Exception::class)
    override fun getSubscriptionIdByCodeId(subscriptionIdByCodeId: Map<Long?, Long?>?) {
        log.info("Got from rabbit subscriptionIdByCodeId = {}", subscriptionIdByCodeId)

        val subscriptionId = subscriptionIdByCodeId?.keys?.firstOrNull() ?: throw Exception()
        val codeId = subscriptionIdByCodeId.values.firstOrNull() ?: throw Exception()

        val subscription = subscriptionRepository.findSubscriptionFullModelById(subscriptionId)?.get()
            ?: throw Exception()

        val code = ActivationCode(
            externalCodeId = codeId,
            subscription = subscription
        )
        subscription.activationCodes?.add(code)

        subscriptionRepository.saveAndFlush(subscription)
        log.info("successfully was updated subscription by id = {}, set code = {}", subscriptionId, codeId)
    }

    @Transactional(rollbackFor = [java.lang.Exception::class])
    @Throws(java.lang.Exception::class)
    override fun save(subscriptionTariffDto: SubscriptionTariffDto?): Subscription {

        var subscription = Subscription(
            name = subscriptionTariffDto?.name,
            phoneNumber = subscriptionTariffDto?.phoneNumber,
            tariff = subscriptionTariffDto?.let { tariffRepository.findById(it.tariffId).get() }
                ?: throw Exception()
        )
        subscription = subscriptionRepository.saveAndFlush(subscription)
        log.info("subscription was saved in postgres by id ={}", subscription.id)

        streamBridge.send(
            "subscriptionIdByTariffId-out-0", MessageBuilder.withPayload(
                java.util.Map.of(subscription.id, subscription.tariff?.tariff_id)
            ).build()
        )

        log.info("Successfully was created subscription - {}", subscription)
        return subscription
    }
}