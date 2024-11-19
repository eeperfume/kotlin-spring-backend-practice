package com.example.practice.product.dto

data class CreateProductRequest(
    override val name: String,
    override val price: Int,
) : BaseProductRequest(name, price)