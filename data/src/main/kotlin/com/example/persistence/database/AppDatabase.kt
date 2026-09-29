package com.example.persistence.database

import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.example.persistence.cart.CartDao
import com.example.persistence.cart.CartItemEntity
import com.example.persistence.product.ProductDao
import com.example.persistence.product.ProductEntity

@Database(
    entities = [ProductEntity::class, CartItemEntity::class],
    version = 6,
)
@ColumnTypeConverters(ProductImageConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    abstract fun cartDao(): CartDao
}
