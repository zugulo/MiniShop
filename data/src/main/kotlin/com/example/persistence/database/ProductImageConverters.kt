package com.example.persistence.database

import androidx.room3.ColumnTypeConverter
import androidx.room3.ProvidedColumnTypeConverter
import com.example.persistence.product.ProductImageEntity
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@ProvidedColumnTypeConverter
class ProductImageConverters(
    val json: Json,
) {
    @ColumnTypeConverter
    fun fromJsonToListOfImageEntites(jsonString: String): List<ProductImageEntity> =
        try {
            json.decodeFromString(listSerializer, jsonString)
        } catch (_: SerializationException) {
            emptyList()
        }

    @ColumnTypeConverter
    fun fromListOfImagesToJson(images: List<ProductImageEntity>): String =
        json.encodeToString(listSerializer, images)

    private val listSerializer: KSerializer<List<ProductImageEntity>> =
        ListSerializer(ProductImageEntity.serializer())
}
