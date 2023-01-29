package com.example.rabbitproducer.entities

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "subscription")
data class Subscription(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String? = null,

    @Column(name = "phone_number")
    val phoneNumber: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm['Z']")
    @CreationTimestamp
    @Column(name = "create_datetime")
    val createDateTime: LocalDateTime? = null,

    @OneToMany(mappedBy = "subscription", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val activationCodes: MutableList<ActivationCode>? = mutableListOf(),

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "tariff_id")
    val tariff: Tariff? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Subscription

        if (id != other.id) return false
        if (name != other.name) return false
        if (phoneNumber != other.phoneNumber) return false
        if (createDateTime != other.createDateTime) return false
        if (activationCodes != other.activationCodes) return false
        if (tariff != other.tariff) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (phoneNumber?.hashCode() ?: 0)
        result = 31 * result + (createDateTime?.hashCode() ?: 0)
        result = 31 * result + (activationCodes?.hashCode() ?: 0)
        result = 31 * result + (tariff?.hashCode() ?: 0)
        return result
    }
}