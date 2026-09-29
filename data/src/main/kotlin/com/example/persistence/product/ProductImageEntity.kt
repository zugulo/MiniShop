package com.example.persistence.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductImageEntity(
    val url: String,
    val altText: String? = "noAltTextAvailable",
)
