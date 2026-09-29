package com.example.domain.usecase.product

import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import jakarta.inject.Inject

class FetchProductUseCase
    @Inject
    constructor(
        private val productRepository: ProductRepository,
    ) {
        suspend fun execute(query: String): Result<List<Product>> =
            productRepository.updateByQuery(query)
    }
