package com.example.practice.product.service

import com.example.practice.brand.dto.BrandResponse
import com.example.practice.brand.repository.BrandRepository
import com.example.practice.product.dto.CreateProductRequest
import com.example.practice.product.dto.ProductResponse
import com.example.practice.product.dto.UpdateProductRequest
import com.example.practice.product.entity.Product
import com.example.practice.product.repository.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.hibernate.Hibernate
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val brandRepository: BrandRepository // BrandRepository 주입
) {
    fun getAllProducts(): List<ProductResponse> {
        val products: List<ProductResponse> = productRepository.findAll().map {
            Hibernate.initialize(it.brand)

            val brandResponse = it.brand?.let { BrandResponse(it.id, it.name, it.description) }

            ProductResponse(
                id = it.id,
                name = it.name,
                price = it.price,
                brand = brandResponse
            )
        }

        return products
    }

    fun getProductById(id: Long): ProductResponse {
        val product = productRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("The product corresponding to '$id' cannot be found.") }

        // 브랜드 객체 초기화 (LAZY 로딩을 강제로 수행)
        Hibernate.initialize(product.brand)

        // brand가 null이 아닐 때만 변환하여 사용
        val brandResponse = product.brand?.let { BrandResponse(it.id, it.name, it.description) }

        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            brand = brandResponse
        )
    }

    fun createProduct(createProductRequest: CreateProductRequest): ProductResponse {
        // Brand를 조회하거나 없으면 예외 처리
        val brand = brandRepository.findById(createProductRequest.brandId)
            .orElseThrow { throw EntityNotFoundException("The brand corresponding to '${createProductRequest.brandId}' cannot be found.") }

        val createdProduct = Product(
            name = createProductRequest.name,
            price = createProductRequest.price,
            brand = brand)

        val savedProduct = productRepository.save(createdProduct)

        // brand가 null이 아닐 때만 변환하여 사용
        val brandResponse = brand?.let { BrandResponse(it.id, it.name, it.description) }

        return ProductResponse(
            id = savedProduct.id,
            name = savedProduct.name,
            price = savedProduct.price,
            brand = brandResponse
        )
    }

    fun updateProduct(id: Long, updateProductRequest: UpdateProductRequest): ProductResponse {
        val existingProduct = productRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("The product corresponding to '$id' cannot be found.") }

        val updatedProduct = existingProduct.copy(
            name = updateProductRequest.name,
            price = updateProductRequest.price
        )

        val savedProduct = productRepository.save(updatedProduct)

        Hibernate.initialize(savedProduct.brand)

        val brandResponse = savedProduct.brand?.let { BrandResponse(it.id, it.name, it.description) }

        return ProductResponse(
            id = savedProduct.id,
            name = savedProduct.name,
            price = savedProduct.price,
            brand = brandResponse
        )

    }

    fun deleteProduct(id: Long) {
        if (!productRepository.existsById(id)) {
            throw EntityNotFoundException("The product corresponding to '$id' cannot be found.")
        }
        productRepository.deleteById(id)
    }
}
