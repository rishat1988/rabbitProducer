package com.example.rabbitproducer.controllers

import com.example.rabbitproducer.dtos.SubscriptionTariffDto
import com.example.rabbitproducer.services.impl.SubscriptionServiceImpl
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
class SubscriptionController(private val subscriptionService: SubscriptionServiceImpl) {
    @PostMapping
    @Throws(Exception::class)
    fun saveSubscription(@RequestBody subscriptionTariffDto: SubscriptionTariffDto): ResponseEntity<Any> {
        val subscription = subscriptionService.save(subscriptionTariffDto)
        return ResponseEntity.ok().body(subscription)
    }
}