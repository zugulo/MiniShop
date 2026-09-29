package com.example.domain.usecase.product

import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import jakarta.inject.Inject

class ChangeFavouriteStatusUseCase
    @Inject
    constructor(
        private val productRepository: ProductRepository,
    ) {
        suspend fun execute(product: Product) {
            productRepository.upsert(product.copy(isFavourite = !product.isFavourite))
        }
    }
