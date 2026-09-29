package com.example.domain.usecase.cart

import com.example.domain.entity.CartItem
import com.example.domain.repository.CartRepository
import jakarta.inject.Inject

class UpdateItemInCartUseCase
    @Inject
    constructor(
        private val cartRepository: CartRepository,
    ) {
        suspend fun execute(
            cartItem: CartItem,
            quantity: Int,
        ) {
            when (quantity) {
                0 -> cartRepository.delete(cartItem)
                else -> cartRepository.upsert(CartItem(cartItem.product, quantity))
            }
        }
    }
