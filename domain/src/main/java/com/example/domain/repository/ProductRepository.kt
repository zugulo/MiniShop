package com.example.domain.repository

import com.example.domain.entity.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun findByEan(ean: String): Product?

    fun findAllQuery(query: String): Flow<List<Product>>

    suspend fun updateByQuery(query: String): Result<List<Product>>

    suspend fun upsert(product: Product)
}
