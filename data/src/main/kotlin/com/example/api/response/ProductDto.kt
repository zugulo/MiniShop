package com.example.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("name") val name: String,
    @SerialName("ean") val ean: String,
    @SerialName("code") val code: String,
    @SerialName("stock") val stock: StockDto,
    @SerialName("pricing") val pricing: PricingDto,
    @SerialName("brandName") val brandName: String? = "noBrand",
    @SerialName("images") val images: List<ProductImageDto>,
)
