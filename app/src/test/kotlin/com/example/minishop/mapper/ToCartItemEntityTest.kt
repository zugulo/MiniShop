package com.example.minishop.mapper

import com.example.domain.entity.CartItem
import com.example.mapper.toCartItemEntity
import com.example.test.TestData
import org.junit.Assert.assertEquals
import org.junit.Test

class ToCartItemEntityTest {
    @Test
    fun cartItemToEntity() {
        val cartItem = CartItem(TestData.testProduct, 0)
        val result = toCartItemEntity(cartItem)
        assertEquals(cartItem.quantity, result.quantity)
        assertEquals(cartItem.product.ean, result.productEan)
    }
}
