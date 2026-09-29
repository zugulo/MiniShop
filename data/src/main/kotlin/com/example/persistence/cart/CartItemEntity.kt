package com.example.persistence.cart

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity
data class CartItemEntity(
    @PrimaryKey
    val productEan: String,
    val quantity: Int,
)
