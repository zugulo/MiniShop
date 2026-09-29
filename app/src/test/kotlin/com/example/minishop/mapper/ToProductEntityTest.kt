package com.example.minishop.mapper

import com.example.mapper.toProductEntity
import com.example.test.TestData
import org.junit.Assert.assertEquals
import org.junit.Test

class ToProductEntityTest {
    @Test
    fun toProductEntityTest() {
        val product = TestData.testProduct
        val result = toProductEntity(product)
        assertEquals(product.ean, result.ean)
        assertEquals(product.code, result.code)
        assertEquals(product.name, result.name)
        assertEquals(product.price, result.price, 0.001f)
        assertEquals(product.isFavourite, result.isFavourite)
        assertEquals(product.inStock, result.inStock)
        assertEquals(product.brandName, result.brandName)
        assertEquals(product.images[0].url, result.images[0].url)
        assertEquals(product.images[0].altText, result.images[0].altText)
        assertEquals(product.images.size, result.images.size)
    }
}
