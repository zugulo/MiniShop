package com.example.domain.entity

data class Product(
    val ean: String,
    val name: String,
    val code: String,
    val brandName: String?,
    val inStock: Boolean,
    val price: Float,
    val isFavourite: Boolean,
    val images: List<ProductImage>,
)
