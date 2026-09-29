package com.example.minishop.useCase

import com.example.api.network.RemoteProductDataSource
import com.example.domain.repository.ProductRepository
import com.example.domain.usecase.product.ChangeFavouriteStatusUseCase
import com.example.persistence.product.LocalProductDataSource
import com.example.persistence.product.ProductRepositoryImpl
import com.example.test.TestData
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ChangeFavouriteUseCaseTest {
    private val localProductDataSource: LocalProductDataSource = mock()
    private val remoteProductDataSource: RemoteProductDataSource = mock()
    private val productRepository: ProductRepository =
        ProductRepositoryImpl(localProductDataSource, remoteProductDataSource)

    val changeFavouriteUseCase: ChangeFavouriteStatusUseCase =
        ChangeFavouriteStatusUseCase(productRepository)

    @Test
    fun favouriteStatusIsSwitchedCorrectly() =
        runTest {
            whenever(
                localProductDataSource.upsert(
                    TestData.testProduct,
                ),
            ).thenReturn(Unit)

            changeFavouriteUseCase.execute(TestData.testProduct.copy(isFavourite = false))

            verify(localProductDataSource).upsert(TestData.testProduct)
        }
}
