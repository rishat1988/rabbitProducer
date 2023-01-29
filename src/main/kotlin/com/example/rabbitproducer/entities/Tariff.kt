package com.example.rabbitproducer.entities

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tariff")
class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     val tariff_id: Long = 0
     val name: String? = null

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm['Z']")
    @CreationTimestamp
    @Column(name = "create_datetime")
     val createDateTime: LocalDateTime? = null
}