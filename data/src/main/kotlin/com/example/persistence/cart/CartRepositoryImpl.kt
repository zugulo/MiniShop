package com.example.persistence.cart

import com.example.domain.entity.Cart
import com.example.domain.entity.CartItem
import com.example.domain.repository.CartRepository
import jakarta.inject.Inject

class CartRepositoryImpl
    @Inject
    constructor(
        private val localCartDataSource: LocalCartDataSource,
    ) : CartRepository {
        override fun getCart(): Cart = localCartDataSource.getCart()

        override suspend fun upsert(cartItem: CartItem) {
            localCartDataSource.upsert(cartItem)
        }

        override suspend fun delete(cartItem: CartItem) {
            localCartDataSource.delete(cartItem)
        }
    }
