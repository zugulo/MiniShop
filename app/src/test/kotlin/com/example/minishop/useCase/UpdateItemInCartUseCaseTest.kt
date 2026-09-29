package com.example.minishop.useCase

import com.example.domain.repository.CartRepository
import com.example.domain.usecase.cart.UpdateItemInCartUseCase
import com.example.test.TestData
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class UpdateItemInCartUseCaseTest {
    private val cartRepository: CartRepository = mock()

    private val updateItemInCartUseCase: UpdateItemInCartUseCase =
        UpdateItemInCartUseCase(cartRepository)

    @Test
    fun useCaseUsesUpsertCorrectlyWhenQuantityIsMoreThan0() =
        runTest {
            whenever(
                cartRepository.upsert(TestData.cartItem),
            ).thenReturn(Unit)

            updateItemInCartUseCase.execute(TestData.cartItem.copy(quantity = 25), 3)

            verify(cartRepository).upsert(TestData.cartItem)
        }

    @Test
    fun useCaseUsesDeleteCorrectlyWhenQuantityIs0() =
        runTest {
            whenever(
                cartRepository.delete(TestData.cartItem),
            ).thenReturn(Unit)

            updateItemInCartUseCase.execute(TestData.cartItem, 0)

            verify(cartRepository).delete(TestData.cartItem)
        }
}
