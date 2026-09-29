package com.example.persistence.cart

import androidx.room3.Embedded
import androidx.room3.Relation
import com.example.persistence.product.ProductEntity

data class CartItemWithProduct(
    @Embedded val cartItemEntity: CartItemEntity,
    @Relation(
        parentColumns = ["productEan"],
        entityColumns = ["ean"],
    )
    val product: ProductEntity,
)
