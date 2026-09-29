package com.example.presentation.ui.cart

import com.example.domain.entity.CartItem

data class CartState(
    val cartItems: List<CartItem>,
    val total: Float,
)
