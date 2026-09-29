package com.example.minishop.dataSource

import com.example.api.network.ProductApi
import com.example.api.network.RemoteProductDataSource
import com.example.api.response.PaginationDto
import com.example.api.response.RemoteProductListResponse
import com.example.test.TestData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class RemoteProductDataSourceTest {
    private val productApi: ProductApi = mock()

    private val remoteProductDataSource: RemoteProductDataSource =
        RemoteProductDataSource(productApi)

    @Test
    fun apiThrowsErrorAndDataSourceReturnsFailure() =
        runTest {
            whenever(
                productApi.getProducts(
                    "de",
                    query = "Hund",
                    page = 1,
                ),
            ).thenThrow(RuntimeException("example"))
            val result = remoteProductDataSource.fetchProducts("Hund")
            assert(result.isFailure)
        }

    @Test
    fun dataSourceReturnsSuccess() =
        runTest {
            whenever(
                productApi.getProducts(
                    "de",
                    query = "Hund",
                    page = 0,
                    pageSize = 40,
                ),
            ).thenReturn(
                RemoteProductListResponse(
                    PaginationDto(
                        currentPage = 1,
                        pageSize = 1,
                        totalPages = 1,
                        totalResults = 1,
                    ),
                    products = listOf(TestData.testProductDto),
                ),
            )

            val result = remoteProductDataSource.fetchProducts("Hund")

            check(result.isSuccess)
            assertEquals(TestData.testProduct.copy(isFavourite = false), result.getOrNull()?.get(0))
        }
}
