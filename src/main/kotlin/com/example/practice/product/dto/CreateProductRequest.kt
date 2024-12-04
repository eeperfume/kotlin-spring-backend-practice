package com.example.practice.product.dto

data class CreateProductRequest(
    override val name: String,
    override val price: Int,
    val brandId: Long // 추가된 필드
) : BaseProductRequest(name, price)
