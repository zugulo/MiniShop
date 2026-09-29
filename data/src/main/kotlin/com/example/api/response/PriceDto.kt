package com.example.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceDto(
    @SerialName("value") val value: Float,
    @SerialName("formattedValue") val formattedValue: String,
    @SerialName("priceType") val priceType: String,
)
