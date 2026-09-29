package com.example.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockDto(
    @SerialName("stockLevelStatus") val stockLevelStatus: String,
)
