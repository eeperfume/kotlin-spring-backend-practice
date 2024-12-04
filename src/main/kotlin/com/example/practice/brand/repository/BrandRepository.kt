package com.example.practice.brand.repository

import com.example.practice.brand.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrandRepository : JpaRepository<Brand, Long> {
    // LIKE 쿼리를 메서드 네이밍으로 작성
    fun findByNameContainingIgnoreCase(name: String): List<Brand>
}
