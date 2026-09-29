package com.example.minishop.integrationTest.setup

import com.example.persistence.cart.CartDao
import com.example.persistence.cart.CartItemEntity
import com.example.persistence.cart.CartItemWithProduct
import com.example.persistence.database.DataBaseModule
import com.example.persistence.product.ProductDao
import com.example.persistence.product.ProductEntity
import com.example.persistence.product.UpdateProductEntity
import com.example.test.TestData
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataBaseModule::class],
)
internal object FakeDataBaseModule {
    @Provides
    @Singleton
    fun provideProductDao(): ProductDao {
        return object : ProductDao {
            override fun findAllByQuery(query: String): Flow<List<ProductEntity>> = flowOf(listOf())

            override suspend fun findByEan(ean: String): ProductEntity? {
                if (ean == "noProduct") {
                    return null
                }
                return TestData.testProductEntity
            }

            override suspend fun findCountOfEan(ean: String): Int {
                TODO("Not yet implemented")
            }

            override suspend fun findListOfExistingProducts(
                listOfEans: List<String>,
            ): List<String> {
                TODO("Not yet implemented")
            }

            override suspend fun upsert(product: ProductEntity) {
                TODO("Not yet implemented")
            }

            override suspend fun upsertAll(products: List<ProductEntity>) {
                TODO("Not yet implemented")
            }

            override suspend fun updatePartialProduct(product: UpdateProductEntity) {
                TODO("Not yet implemented")
            }

            override suspend fun updatePartialProductList(product: List<UpdateProductEntity>) {
                TODO("Not yet implemented")
            }
        }
    }

    @Provides
    @Singleton
    fun provideCartDao(): CartDao =
        object : CartDao {
            override fun getCart(): Flow<List<CartItemWithProduct>> {
                TODO("Not yet implemented")
            }

            override suspend fun upsert(cartItemEntity: CartItemEntity) {
                TODO("Not yet implemented")
            }

            override suspend fun delete(cartItemEntity: CartItemEntity) {
                TODO("Not yet implemented")
            }
        }
}
