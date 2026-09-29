package com.example.minishop.integrationTest.useCase

import com.example.domain.usecase.product.GetProductUseCase
import com.example.test.TestData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import jakarta.inject.Inject
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class GetProductUseCaseTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getProductUseCase: GetProductUseCase

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun getProduct() =
        runTest {
            val result = getProductUseCase.execute("doesntMatterBecauseItsTheFakeImplementation")
            checkNotNull(result)
            Assert.assertEquals(TestData.testProductEntity.ean, result.ean)
            Assert.assertEquals(TestData.testProductEntity.code, result.code)
            Assert.assertEquals(TestData.testProductEntity.name, result.name)
            Assert.assertEquals(TestData.testProductEntity.price, result.price, 0.001f)
            Assert.assertEquals(TestData.testProductEntity.isFavourite, result.isFavourite)
            Assert.assertEquals(TestData.testProductEntity.inStock, result.inStock)
            Assert.assertEquals(TestData.testProductEntity.brandName, result.brandName)
            Assert.assertEquals(TestData.testProductEntity.images[0].url, result.images[0].url)
            Assert.assertEquals(
                TestData.testProductEntity.images[0].altText,
                result.images[0].altText,
            )
            Assert.assertEquals(TestData.testProductEntity.images.size, result.images.size)
        }

    @Test
    fun productNotFound() =
        runTest {
            val result = getProductUseCase.execute("noProduct")
            Assert.assertEquals(null, result)
        }
}
