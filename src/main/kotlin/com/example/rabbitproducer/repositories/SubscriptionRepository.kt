package com.example.rabbitproducer.repositories

import com.example.rabbitproducer.entities.Subscription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SubscriptionRepository : JpaRepository<Subscription?, Long?> {
    @Query(value = "SELECT s from Subscription  s " +
            "left join fetch s.activationCodes " +
            "left join fetch s.tariff where s.id = ?1")
    fun findSubscriptionFullModelById(id: Long): Optional<Subscription?>?
}