package com.example.presentation.ui.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CartItem
import com.example.domain.entity.Product
import com.example.domain.usecase.cart.GetCartUseCase
import com.example.domain.usecase.cart.UpdateItemInCartUseCase
import com.example.domain.usecase.product.ChangeFavouriteStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class CartViewModel
@Inject
constructor(
    private val getCartUseCase: GetCartUseCase,
    private val updateItemInCartUseCase: UpdateItemInCartUseCase,
    private val changeFavouriteStatusUseCase: ChangeFavouriteStatusUseCase,
) : ViewModel() {
    /*
            val state: StateFlow<CartState> =
                getCartUseCase
                    .execute()
                    .entries
                    .map {
                        CartState(
                            cartItems = it,
                            total =
                                it
                                    .map { item: CartItem ->
                                        item.quantity * item.product.price
                                    }.sum(),
                        )
                    }.stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000),
                        initialValue = CartState(emptyList(), 0f),
                    )
    */

    private val _state = MutableStateFlow(CartState(emptyList(), 0f))
    val state: StateFlow<CartState> = _state
        .onSubscription {
            loadCart()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            CartState(emptyList(), 0f)
        )

    private fun loadCart() {
        viewModelScope.launch {
            getCartUseCase.execute().entries.collect {
                _state.value =
                    _state.value.copy(
                        cartItems = it,
                        total =
                            it
                                .map { item: CartItem ->
                                    item.quantity * item.product.price
                                }.sum(),
                    )
            }
        }
    }

    fun changeFavouriteStatus(product: Product) {
        viewModelScope.launch {
            changeFavouriteStatusUseCase.execute(product)
        }
    }

    fun update(
        cartItem: CartItem,
        quantity: Int,
    ) {
        viewModelScope.launch {
            Log.d("Updating CartItem", "$cartItem to $quantity")
            updateItemInCartUseCase.execute(cartItem, quantity)
        }
    }
}
