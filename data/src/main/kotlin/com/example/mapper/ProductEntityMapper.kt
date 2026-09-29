package com.example.mapper

import com.example.domain.entity.Product
import com.example.domain.entity.ProductImage
import com.example.persistence.product.ProductEntity
import com.example.persistence.product.ProductImageEntity
import com.example.persistence.product.UpdateProductEntity

fun toProductEntity(product: Product): ProductEntity =
    ProductEntity(
        ean = product.ean,
        name = product.name,
        brandName = product.brandName,
        inStock = product.inStock,
        price = product.price,
        images = toProductImageEntity(product.images),
        code = product.code,
        isFavourite = product.isFavourite,
    )

fun toUpdateProductEntity(product: Product): UpdateProductEntity =
    UpdateProductEntity(
        ean = product.ean,
        name = product.name,
        brandName = product.brandName,
        inStock = product.inStock,
        price = product.price,
        images = toProductImageEntity(product.images),
        code = product.code,
    )

private fun toProductImageEntity(images: List<ProductImage>): List<ProductImageEntity> =
    images.map {
        ProductImageEntity(
            url = it.url,
            altText = it.altText,
        )
    }
