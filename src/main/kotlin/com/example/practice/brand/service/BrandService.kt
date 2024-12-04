package com.example.practice.brand.service

import com.example.practice.brand.dto.BrandResponse
import com.example.practice.brand.dto.CreateBrandRequest
import com.example.practice.brand.dto.UpdateBrandRequest
import com.example.practice.brand.entity.Brand
import com.example.practice.brand.repository.BrandRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BrandService(private val brandRepository: BrandRepository) {
    fun getAllBrands(page: Int, size: Int, sort: String): List<BrandResponse> {
        val sortParams = sort.split(",")
        val sortField = sortParams[0]
        val sortOrder = sortParams[1]

        val sortDirection = if (sortOrder.equals("desc", ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC
        val pageable: Pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField))

        val brandPage = brandRepository.findAll(pageable)

        return brandPage.content.map {
            BrandResponse(id = it.id, name = it.name, description = it.description)
        }
    }

    fun getBrandById(id: Long): BrandResponse {
        val brand = brandRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("The brand corresponding to '$id' cannot be found.") }
        return BrandResponse(id = brand.id, name = brand.name, description = brand.description)
    }
    // Q.함수의 반환 타입이 Optional<Brand>가 아닌 Brand인 이유:
    //  - orElseThrow 함수가 값이 존재하면 Optional의 내부 값을 추출해 반환해 주고, 값이 없으면 예외를 던지도록 되어 있다.
    //  - 결과적으로 타입 narrowing한 것처럼 동작하게 된다.

    fun createBrand(createBrandRequest: CreateBrandRequest): BrandResponse {
        val createdBrand = Brand(name = createBrandRequest.name, description = createBrandRequest.description)
        val savedBrand = brandRepository.save(createdBrand)
        return BrandResponse(id = savedBrand.id, name = savedBrand.name, description = savedBrand.description)
    }

    fun updateBrand(id: Long, updateBrandRequest: UpdateBrandRequest): BrandResponse {
        val existingBrand = brandRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException("The brand corresponding to '$id' cannot be found.") }
        val updatedBrand =
            existingBrand.copy(name = updateBrandRequest.name, description = updateBrandRequest.description)
        val savedBrand = brandRepository.save(updatedBrand)
        return BrandResponse(id = savedBrand.id, name = savedBrand.name, description = savedBrand.description)

    }

    fun deleteBrand(id: Long) {
        if (!brandRepository.existsById(id)) {
            throw EntityNotFoundException("The brand corresponding to '$id' cannot be found.")
        }
        brandRepository.deleteById(id)
    }
}
