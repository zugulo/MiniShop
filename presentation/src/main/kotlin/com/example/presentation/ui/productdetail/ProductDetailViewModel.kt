package com.example.presentation.ui.productdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Product
import com.example.domain.usecase.cart.AddProductToCartUseCase
import com.example.domain.usecase.product.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProductDetailViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
        private val addProductToCartUseCase: AddProductToCartUseCase,
        private val getProductUseCase: GetProductUseCase,
    ) : ViewModel() {
        private val ean: String = checkNotNull(savedStateHandle["ean"])

        private val _state =
            MutableStateFlow(ProductDetailState(null))
        val state: StateFlow<ProductDetailState> = _state.asStateFlow()

        init {
            loadProduct(ean)
        }

        fun addProductToCart(
            product: Product,
            quantity: Int,
        ) {
            Log.d("Adding Product to Cart", "$product with quantity $quantity")
            viewModelScope.launch {
                addProductToCartUseCase.execute(product, quantity)
            }
        }

        fun loadProduct(ean: String) {
            viewModelScope.launch {
                Log.d("Ean for ProductDetails", ean)
                val product = getProductUseCase.execute(ean)
                _state.value = _state.value.copy(product = product)
            }
        }
    }
