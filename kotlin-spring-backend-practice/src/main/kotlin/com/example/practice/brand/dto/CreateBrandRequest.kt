package com.example.practice.brand.dto

data class CreateBrandRequest(
    override val name: String,
    override val description: String?
) : BaseBrandRequest(name, description)
