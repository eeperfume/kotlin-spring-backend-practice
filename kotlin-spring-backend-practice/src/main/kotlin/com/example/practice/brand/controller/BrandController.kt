package com.example.practice.brand.controller

import com.example.practice.brand.dto.BrandResponse
import com.example.practice.brand.dto.CreateBrandRequest
import com.example.practice.brand.dto.UpdateBrandRequest
import com.example.practice.brand.service.BrandService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/brand")
class BrandController(private val brandService: BrandService) {
    // 모든 브랜드 조회 (GET /brand)
    @GetMapping
    fun getAllBrands(): ResponseEntity<List<BrandResponse>> {
        val brands = brandService.getAllBrands()
        return ResponseEntity.ok(brands)
    }

    // 브랜드 조회 (GET /brand/{id})
    @GetMapping("/{id}")
    fun getBrandById(@PathVariable id: Long): ResponseEntity<BrandResponse> {
        val brand = brandService.getBrandById(id)
        return ResponseEntity.ok(brand)
    }

    // 브랜드 생성 (POST /brand)
    @PostMapping
    fun createBrand(@RequestBody createBrandRequest: CreateBrandRequest): ResponseEntity<BrandResponse> {
        val brand = brandService.createBrand(createBrandRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(brand)
    }

    // 브랜드 업데이트 (PUT /brand/{id})
    @PutMapping("/{id}")
    fun updateBrand(
        @PathVariable id: Long,
        @RequestBody updateBrandRequest: UpdateBrandRequest
    ): ResponseEntity<BrandResponse> {
        val brand = brandService.updateBrand(id, updateBrandRequest)
        return ResponseEntity.ok(brand)
    }

    // 브랜드 삭제 (DELETE /brand/{id})
    @DeleteMapping("/{id}")
    fun deleteBrand(@PathVariable id: Long): ResponseEntity<BrandResponse> {
        brandService.deleteBrand(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
