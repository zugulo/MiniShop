package com.example.minishop.mapper

import com.example.mapper.toProduct
import com.example.test.TestData
import org.junit.Assert.assertEquals
import org.junit.Test

class ToProductTest {
    @Test
    fun fromEntity() {
        val entity = TestData.testProductEntity
        val result = toProduct(entity)
        assertEquals(entity.ean, result.ean)
        assertEquals(entity.price, result.price, 0.001f)
        assertEquals(entity.code, result.code)
        assertEquals(entity.name, result.name)
        assertEquals(entity.isFavourite, result.isFavourite)
        assertEquals(entity.inStock, result.inStock)
        assertEquals(entity.brandName, result.brandName)
        assertEquals(entity.images[0].url, result.images[0].url)
        assertEquals(entity.images[0].altText, result.images[0].altText)
        assertEquals(entity.images.size, result.images.size)
    }

    @Test
    fun fromDto() {
        val dto = TestData.testProductDto
        val result = toProduct(dto)
        assertEquals(dto.ean, result.ean)
        assertEquals(dto.pricing.current.value, result.price, 0.001f)
        assertEquals(dto.code, result.code)
        assertEquals(dto.name, result.name)
        assertEquals(false, result.isFavourite)
        assertEquals(true, result.inStock)
        assertEquals(dto.brandName, result.brandName)
        assertEquals(dto.images[0].url, result.images[0].url)
        assertEquals(dto.images[0].altText, result.images[0].altText)
        assertEquals(dto.images.size, result.images.size)
    }

    @Test
    fun fromDtoWithNull() {
        val dto = TestData.testProductDtoWithNullValues
        val result = toProduct(dto)
        assertEquals(dto.ean, result.ean)
        assertEquals(dto.pricing.current.value, result.price, 0.001f)
        assertEquals(dto.code, result.code)
        assertEquals(dto.name, result.name)
        assertEquals(false, result.isFavourite)
        assertEquals(false, result.inStock)
        assertEquals(dto.brandName, result.brandName)
        assertEquals(dto.images[0].url, result.images[0].url)
        assertEquals("noAltTextAvailable", result.images[0].altText)
        assertEquals(dto.images.size, result.images.size)
    }
}
