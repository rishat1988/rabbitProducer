package com.example.rabbitproducer.entities

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "activation_code")
data class ActivationCode (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     val activation_code_id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
     val subscription: Subscription? = null,

    @Column(name = "external_code_id")
     val externalCodeId: Long = 0L,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm['Z']")
    @CreationTimestamp
    @Column(name = "create_datetime")
     val createDateTime: LocalDateTime? = null
)
