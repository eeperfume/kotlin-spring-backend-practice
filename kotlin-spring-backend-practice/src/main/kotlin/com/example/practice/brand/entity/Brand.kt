package com.example.practice.brand.entity

import jakarta.persistence.*

@Entity
@Table(name = "brand")
data class Brand(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true, nullable = false)
    val name: String,

    val description: String?,
)
