package com.example.rabbitproducer.repositories

import com.example.rabbitproducer.entities.Tariff
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TariffRepository : JpaRepository<Tariff?, Long?> {

}