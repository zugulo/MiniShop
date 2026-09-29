package com.example.persistence.product

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "products")
data class UpdateProductEntity(
    @PrimaryKey
    val ean: String,
    val name: String,
    val code: String,
    val brandName: String? = "NoBrandNameAvailable",
    val inStock: Boolean,
    val price: Float,
    val images: List<ProductImageEntity>,
)
