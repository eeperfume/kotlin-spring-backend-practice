package com.example.practice.product.controller

import com.example.practice.product.dto.ProductResponse
import com.example.practice.product.dto.CreateProductRequest
import com.example.practice.product.dto.UpdateProductRequest
import com.example.practice.product.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController(private val productService: ProductService) {
    // 모든 제품 조회 (GET /product)
    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductResponse>> {
        val products = productService.getAllProducts()
        return ResponseEntity.ok(products)
    }

    // 제품 조회 (GET /product/{id})
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        val product = productService.getProductById(id)
        return ResponseEntity.ok(product)
    }

    // 제품 생성 (POST /product)
    @PostMapping
    fun createProduct(@RequestBody createProductRequest: CreateProductRequest): ResponseEntity<ProductResponse> {
        val product = productService.createProduct(createProductRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }

    // 제품 업데이트 (PUT /product/{id})
    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody updateProductRequest: UpdateProductRequest
    ): ResponseEntity<ProductResponse> {
        val product = productService.updateProduct(id, updateProductRequest)
        return ResponseEntity.ok(product)
    }

    // 제품 삭제 (DELETE /product/{id})
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        productService.deleteProduct(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
