package com.example.rabbitproducer.services

import com.example.rabbitproducer.dtos.SubscriptionTariffDto
import com.example.rabbitproducer.entities.Subscription

interface SubscriptionService {

    @Throws(Exception::class)
    fun save(subscriptionTariffDto: SubscriptionTariffDto?): Subscription

    @Throws(Exception::class)
    fun getSubscriptionIdByCodeId(subscriptionIdByCodeId: Map<Long?, Long?>?)
}