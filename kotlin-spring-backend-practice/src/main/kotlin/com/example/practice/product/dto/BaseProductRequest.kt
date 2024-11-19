package com.example.practice.product.dto

sealed class BaseProductRequest(
    open val name: String,
    open val price: Int,
)