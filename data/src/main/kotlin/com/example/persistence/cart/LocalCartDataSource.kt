package com.example.persistence.cart

import com.example.domain.entity.Cart
import com.example.domain.entity.CartItem
import com.example.mapper.toCartItemEntity
import com.example.mapper.toProduct
import jakarta.inject.Singleton
import javax.inject.Inject
import kotlinx.coroutines.flow.map

@Singleton
class LocalCartDataSource
    @Inject
    constructor(
        private val dao: CartDao,
    ) {
        suspend fun delete(cartItem: CartItem) {
            dao.delete(toCartItemEntity(cartItem))
        }

        suspend fun upsert(cartItem: CartItem) {
            dao.upsert(toCartItemEntity(cartItem))
        }

        fun getCart(): Cart =
            Cart(
                dao.getCart().map { list: List<CartItemWithProduct> ->
                    list.map { entity: CartItemWithProduct ->
                        CartItem(toProduct(entity.product), entity.cartItemEntity.quantity)
                    }
                },
            )
    }
