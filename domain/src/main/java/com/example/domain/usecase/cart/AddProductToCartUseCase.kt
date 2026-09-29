package com.example.domain.usecase.cart

import com.example.domain.entity.CartItem
import com.example.domain.entity.Product
import com.example.domain.repository.CartRepository
import jakarta.inject.Inject

class AddProductToCartUseCase
    @Inject
    constructor(
        private val cartRepository: CartRepository,
    ) {
        suspend fun execute(
            product: Product,
            quantity: Int,
        ) {
            cartRepository.upsert(CartItem(product, quantity))
        }
    }
