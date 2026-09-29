package com.example.presentation.ui.productlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Product
import com.example.domain.usecase.product.ChangeFavouriteStatusUseCase
import com.example.domain.usecase.product.FetchProductUseCase
import com.example.domain.usecase.product.SearchForProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProductListViewModel
    @Inject
    constructor(
        private val fetchProductUseCase: FetchProductUseCase,
        private val searchForProductUseCase: SearchForProductUseCase,
        private val changeFavouriteStatusUseCase: ChangeFavouriteStatusUseCase,
    ) : ViewModel() {
        private val _state =
            MutableStateFlow(
                ProductListState(
                    products = emptyList(),
                    isLoading = false,
                    isError = false,
                ),
            )
        val state: StateFlow<ProductListState> = _state.asStateFlow()

        var job: Job? = null

        init {
            initialProductLoading()
        }

        fun initialProductLoading() {
            job =
                viewModelScope.launch {
                    searchForProductUseCase.execute("").collect {
                        _state.value = _state.value.copy(products = it)
                    }
                }
        }

        fun changeFavouriteStatus(product: Product) {
            viewModelScope.launch {
                changeFavouriteStatusUseCase.execute(product)
            }
        }

        fun loadProductsByQuery(query: String) {
            viewModelScope.launch {
                Log.d("ProductQuery", query)
                _state.value = _state.value.copy(isLoading = true)
                val result = fetchProductUseCase.execute(query)
                _state.value = _state.value.copy(isError = result.isFailure, isLoading = false)
            }

            viewModelScope.launch {
                job?.cancelAndJoin()

                job =
                    viewModelScope.launch {
                        searchForProductUseCase.execute(query).collect {
                            _state.value = _state.value.copy(products = it)
                        }
                    }
            }
        }
    }
