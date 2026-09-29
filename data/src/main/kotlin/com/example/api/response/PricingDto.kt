package com.example.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PricingDto(
    @SerialName("current") val current: PriceDto,
    @SerialName("former") val former: PriceDto? = null,
    @SerialName("formerPricePerUnit") val formerPricePerUnit: PriceDto? = null,
    @SerialName("perUnit") val perUnit: PriceDto? = null,
)
