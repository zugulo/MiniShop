package com.example.test

import com.example.api.response.PriceDto
import com.example.api.response.PricingDto
import com.example.api.response.ProductDto
import com.example.api.response.ProductImageDto
import com.example.api.response.StockDto
import com.example.domain.entity.Cart
import com.example.domain.entity.CartItem
import com.example.domain.entity.Product
import com.example.domain.entity.ProductImage
import com.example.persistence.product.ProductEntity
import com.example.persistence.product.ProductImageEntity
import kotlinx.coroutines.flow.flowOf

object TestData {
    private const val URL =
        ""//imagine a random banner
    private const val ALT_TEXT = "rinti-dose"
    val testProduct =
        Product(
            name = "Rinti",
            price = 3.99f,
            ean = "1234567890123",
            brandName = "noBrandName",
            inStock = true,
            code = "123871634",
            images =
                listOf(
                    ProductImage(
                        url = URL,
                        altText = ALT_TEXT,
                    ),
                ),
            isFavourite = true,
        )

    val testProductEntity =
        ProductEntity(
            name = "Rinti",
            price = 3.99f,
            ean = "1234567890123",
            brandName = "noBrandName",
            inStock = true,
            code = "123871634",
            images =
                listOf(
                    ProductImageEntity(
                        url = URL,
                        altText = ALT_TEXT,
                    ),
                ),
            isFavourite = true,
        )

    val testProductDto =
        ProductDto(
            name = "Rinti",
            ean = "1234567890123",
            brandName = "noBrandName",
            code = "123871634",
            images =
                listOf(
                    ProductImageDto(
                        url = URL,
                        altText = ALT_TEXT,
                    ),
                ),
            stock = StockDto("inStock"),
            pricing =
                PricingDto(
                    current =
                        PriceDto(
                            value = 3.99f,
                            formattedValue = "3.99 €",
                            priceType = "",
                        ),
                    former = null,
                    formerPricePerUnit = null,
                    perUnit = null,
                ),
        )

    val testProductDtoWithNullValues =
        ProductDto(
            name = "Rinti",
            ean = "1234567890123",
            brandName = "noBrandName",
            code = "123871634",
            images =
                listOf(
                    ProductImageDto(
                        url = URL,
                        altText = null,
                    ),
                ),
            stock = StockDto("NotInStock"),
            pricing =
                PricingDto(
                    current =
                        PriceDto(
                            value = 3.99f,
                            formattedValue = "3.99 €",
                            priceType = "",
                        ),
                    former = null,
                    formerPricePerUnit = null,
                    perUnit = null,
                ),
        )

    val cartItem =
        CartItem(
            product = testProduct,
            quantity = 3,
        )

    val cart =
        Cart(
            flowOf(listOf(cartItem)),
        )
}
