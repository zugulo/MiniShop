package com.example.persistence.product

import com.example.domain.entity.Product
import com.example.mapper.toProduct
import com.example.mapper.toProductEntity
import com.example.mapper.toUpdateProductEntity
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class LocalProductDataSource
    @Inject
    constructor(
        private val dao: ProductDao,
    ) {
        suspend fun upsertAll(products: List<Product>) {
            val listOfExistingEans =
                dao.findListOfExistingProducts(
                    products.map {
                        it.ean
                    },
                )
            dao.updatePartialProductList(
                products
                    .filter { listOfExistingEans.contains(it.ean) }
                    .map(::toUpdateProductEntity),
            )
            dao.upsertAll(
                products
                    .filter { !listOfExistingEans.contains(it.ean) }
                    .map(::toProductEntity),
            )
        }

        suspend fun upsert(product: Product) {
            dao.upsert(toProductEntity(product))
        }

        suspend fun findByEan(ean: String): Product? {
            val a = dao.findByEan(ean = ean)
            return if (a != null) {
                toProduct(a)
            } else {
                null
            }
        }

        fun findAllByQuery(query: String): Flow<List<Product>> =
            dao.findAllByQuery("%$query%").map { list ->
                list.map(::toProduct)
            }
    }
