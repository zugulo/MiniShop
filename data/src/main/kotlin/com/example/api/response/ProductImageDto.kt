package com.example.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductImageDto(
    @SerialName("url") val url: String,
    @SerialName("altText") val altText: String? = "",
)
