package com.example.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteProductListResponse(
    @SerialName("pagination") val pagination: PaginationDto,
    @SerialName("products") val products: List<ProductDto>,
)
