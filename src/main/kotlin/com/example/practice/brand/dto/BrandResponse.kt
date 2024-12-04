package com.example.practice.brand.dto

/**
 * BrandResponse의 속성 타입은 엔티티 클래스의 해당 속성 타입과 일치해야 한다.
 */
data class BrandResponse(
    val id: Long,
    val name: String,
    val description: String?,
)
