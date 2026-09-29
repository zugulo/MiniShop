package com.example.domain.entity

import kotlinx.coroutines.flow.Flow

data class Cart(
    val entries: Flow<List<CartItem>>,
)
