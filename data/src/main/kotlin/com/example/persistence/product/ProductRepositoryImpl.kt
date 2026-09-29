package com.example.persistence.product

import com.example.api.network.RemoteProductDataSource
import com.example.domain.entity.Product
import com.example.domain.repository.ProductRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl
    @Inject
    constructor(
        private val localProductDataSource: LocalProductDataSource,
        private val remoteProductDataSource: RemoteProductDataSource,
    ) : ProductRepository {
        override suspend fun findByEan(ean: String): Product? =
            localProductDataSource.findByEan(ean = ean)

        override fun findAllQuery(query: String): Flow<List<Product>> =
            localProductDataSource.findAllByQuery(query)

        override suspend fun updateByQuery(query: String): Result<List<Product>> {
            val result = remoteProductDataSource.fetchProducts(query)
            when {
                result.isFailure -> return result
                result.isSuccess -> localProductDataSource.upsertAll(result.getOrNull()!!)
            }
            return result
        }

        override suspend fun upsert(product: Product) {
            localProductDataSource.upsert(product)
        }
    }
