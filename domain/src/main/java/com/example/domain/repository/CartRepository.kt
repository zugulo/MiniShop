package com.example.domain.repository

import com.example.domain.entity.Cart
import com.example.domain.entity.CartItem

interface CartRepository {
    fun getCart(): Cart

    suspend fun upsert(cartItem: CartItem)

    suspend fun delete(cartItem: CartItem)
}
