package com.example.domain.usecase.product

import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class SearchForProductUseCase
    @Inject
    constructor(
        private val productRepository: ProductRepository,
    ) {
        fun execute(query: String): Flow<List<Product>> = productRepository.findAllQuery(query)
    }
