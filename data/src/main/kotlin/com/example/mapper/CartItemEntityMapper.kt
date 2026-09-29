package com.example.mapper

import com.example.domain.entity.CartItem
import com.example.persistence.cart.CartItemEntity

fun toCartItemEntity(cartItem: CartItem): CartItemEntity =
    CartItemEntity(
        productEan = cartItem.product.ean,
        quantity = cartItem.quantity,
    )
