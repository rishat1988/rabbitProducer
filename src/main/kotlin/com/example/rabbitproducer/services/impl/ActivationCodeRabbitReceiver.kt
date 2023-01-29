package com.example.rabbitproducer.services.impl

import com.example.rabbitproducer.entities.ActivationCode
import com.example.rabbitproducer.services.SubscriptionService
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
@Slf4j
class ActivationCodeRabbitReceiver (   private val subscriptionService: SubscriptionService){
    private val log = LoggerFactory.getLogger(ActivationCodeRabbitReceiver::class.java)

    @Bean
    fun subscriptionIdByCodeId(): Consumer<Map<Long?, Long?>> {
        return Consumer<Map<Long?, Long?>> { subscriptionIdByCodeId: Map<Long?, Long?>? ->
            log.info("successfully got from rabbit - {}", subscriptionIdByCodeId)
            try {
                subscriptionService.getSubscriptionIdByCodeId(subscriptionIdByCodeId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}