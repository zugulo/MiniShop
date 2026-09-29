package com.example.persistence.product

import androidx.room3.Dao
import androidx.room3.Query
import androidx.room3.Update
import androidx.room3.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products WHERE name LIKE :query")
    fun findAllByQuery(query: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE ean = :ean")
    suspend fun findByEan(ean: String): ProductEntity?

    @Query("SELECT COUNT(*) FROM products WHERE ean = :ean")
    suspend fun findCountOfEan(ean: String): Int

    @Query("SELECT ean FROM products WHERE ean IN (:listOfEans)")
    suspend fun findListOfExistingProducts(listOfEans: List<String>): List<String>

    @Upsert
    suspend fun upsert(product: ProductEntity)

    @Upsert
    suspend fun upsertAll(products: List<ProductEntity>)

    @Update(entity = ProductEntity::class)
    suspend fun updatePartialProduct(product: UpdateProductEntity)

    @Update(entity = ProductEntity::class)
    suspend fun updatePartialProductList(product: List<UpdateProductEntity>)
}
