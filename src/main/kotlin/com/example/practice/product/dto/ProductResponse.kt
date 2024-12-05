package com.example.practice.product.dto

import com.example.practice.brand.dto.BrandResponse

data class ProductResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val brand: BrandResponse?
)
