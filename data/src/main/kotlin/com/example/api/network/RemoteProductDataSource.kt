package com.example.api.network

import com.example.domain.entity.Product
import com.example.domain.exception.ApiException
import com.example.mapper.toProduct
import jakarta.inject.Inject

class RemoteProductDataSource
    @Inject
    constructor(
        private val api: ProductApi,
    ) {
        suspend fun fetchProducts(query: String): Result<List<Product>> =
            try {
                Result.success(
                    api
                        .getProducts(
                            query = query,
                            page = 0,
                            pageSize = 40,
                        ).products
                        .map(::toProduct),
                )
            } catch (e: Exception) {
                Result.failure(ApiException(e.message))
            }
    }
