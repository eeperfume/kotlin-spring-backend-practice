package com.example.practice.product.repository

import com.example.practice.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
