package com.example.minishop.viewModel

import com.example.domain.entity.Cart
import com.example.domain.usecase.cart.GetCartUseCase
import com.example.domain.usecase.cart.UpdateItemInCartUseCase
import com.example.domain.usecase.product.ChangeFavouriteStatusUseCase
import com.example.presentation.ui.cart.CartViewModel
import com.example.test.TestData
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CartViewModelTest {
    private lateinit var changeFavouriteUseCaseTest: ChangeFavouriteStatusUseCase

    private lateinit var updateItemInCartUseCase: UpdateItemInCartUseCase

    private lateinit var getCartUseCase: GetCartUseCase

    @Test
    fun testViewModelCallsChangeFavouriteUseCase() =
        runTest {
            getCartUseCase = mock()
            updateItemInCartUseCase = mock()
            changeFavouriteUseCaseTest = mock()
            whenever(getCartUseCase.execute()).doReturn(TestData.cart)
            whenever(changeFavouriteUseCaseTest.execute(TestData.testProduct))
                .thenReturn(Unit)
            val viewModel =
                CartViewModel(
                    getCartUseCase = getCartUseCase,
                    updateItemInCartUseCase = updateItemInCartUseCase,
                    changeFavouriteStatusUseCase = changeFavouriteUseCaseTest,
                )
            viewModel.changeFavouriteStatus(product = TestData.testProduct)
            verify(changeFavouriteUseCaseTest).execute(TestData.testProduct)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testViewModelCallsGetCartUseCase() =
        runTest {
            getCartUseCase = mock()
            updateItemInCartUseCase = mock()
            changeFavouriteUseCaseTest = mock()
            whenever(getCartUseCase.execute()).doReturn(TestData.cart)
            val viewModel =
                CartViewModel(
                    getCartUseCase = getCartUseCase,
                    updateItemInCartUseCase = updateItemInCartUseCase,
                    changeFavouriteStatusUseCase = changeFavouriteUseCaseTest,
                )
            verify(getCartUseCase).execute()
            advanceUntilIdle()
            assertEquals(
                TestData.cartItem.product.price * TestData.cartItem.quantity,
                viewModel.state.value.total,
                0.001f,
            )
            advanceUntilIdle()
        }
}
