package com.example.mapper

import com.example.api.response.ProductDto
import com.example.api.response.ProductImageDto
import com.example.domain.entity.Product
import com.example.domain.entity.ProductImage
import com.example.persistence.product.ProductEntity
import com.example.persistence.product.ProductImageEntity

fun toProduct(dto: ProductDto): Product =
    Product(
        ean = dto.ean,
        name = dto.name,
        code = dto.code,
        brandName = dto.brandName,
        inStock = dto.stock.stockLevelStatus == "inStock",
        price = dto.pricing.current.value,
        images = toListOfImagesFromDto(dto.images),
        isFavourite = false,
    )

fun toProduct(entity: ProductEntity): Product =
    Product(
        ean = entity.ean,
        name = entity.name,
        brandName = entity.brandName,
        inStock = entity.inStock,
        price = entity.price,
        images = toListOfImages(entity.images),
        code = entity.code,
        isFavourite = entity.isFavourite,
    )

private fun toListOfImagesFromDto(images: List<ProductImageDto>): List<ProductImage> =
    images
        .map { i ->
            ProductImage(
                url = i.url,
                altText = i.altText ?: "noAltTextAvailable",
            )
        }.toList()

private fun toListOfImages(entities: List<ProductImageEntity>): List<ProductImage> =
    entities
        .map { i ->
            ProductImage(
                url = i.url,
                altText = i.altText ?: "noAltTextAvailable",
            )
        }.toList()
