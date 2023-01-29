package com.example.rabbitproducer.repositories

import com.example.rabbitproducer.entities.ActivationCode
import org.springframework.data.jpa.repository.JpaRepository

interface ActivationCodeRepository : JpaRepository<ActivationCode?, Long?>