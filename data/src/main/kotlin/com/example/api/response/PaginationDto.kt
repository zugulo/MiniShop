package com.example.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationDto(
    @SerialName("currentPage") val currentPage: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("totalPages") val totalPages: Int,
    @SerialName("totalResults") val totalResults: Int,
)
