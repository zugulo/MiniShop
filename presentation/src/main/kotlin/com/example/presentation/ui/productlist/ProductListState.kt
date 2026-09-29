package com.example.presentation.ui.productlist

import com.example.domain.entity.Product

data class ProductListState(
    val products: List<Product>,
    val isLoading: Boolean,
    val isError: Boolean,
)
