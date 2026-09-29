package com.example.domain.usecase.product

import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import jakarta.inject.Inject

class GetProductUseCase
    @Inject
    constructor(
        private val productRepository: ProductRepository,
    ) {
        suspend fun execute(ean: String): Product? = productRepository.findByEan(ean)
    }
