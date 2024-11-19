package com.example.practice.brand.repository

import com.example.practice.brand.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository : JpaRepository<Brand, Long>
