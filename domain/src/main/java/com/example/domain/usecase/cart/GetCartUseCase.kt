package com.example.domain.usecase.cart

import com.example.domain.entity.Cart
import com.example.domain.repository.CartRepository
import jakarta.inject.Inject

class GetCartUseCase
    @Inject
    constructor(
        private val cartRepository: CartRepository,
    ) {
        fun execute(): Cart = cartRepository.getCart()
    }
