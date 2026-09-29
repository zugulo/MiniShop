package com.example.presentation.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.entity.CartItem
import com.example.domain.entity.Product
import com.example.presentation.ui.component.ProductCard
import com.example.presentation.ui.theme.MiniShopTheme
import com.example.test.TestData
import kotlin.math.roundToInt

@Composable
fun CartScreen(
    onProductCardClick: (String) -> Unit,
    onBuyButtonClick: () -> Unit,
    viewModel: CartViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CartScreenContent(
        state = state,
        onProductCardClick = onProductCardClick,
        onBuyButtonClick = onBuyButtonClick,
        onConfirmButtonClick = { cartItem, quantity ->
            viewModel.update(
                cartItem =
                    CartItem(
                        product = cartItem.product,
                        quantity = cartItem.quantity,
                    ),
                quantity = quantity,
            )
        },
        onDismissButtonClick = {
            viewModel.update(
                cartItem =
                    CartItem(
                        product = it.product,
                        quantity = it.quantity,
                    ),
                quantity = 0,
            )
        },
        onFavouriteButtonClick = {
            viewModel.changeFavouriteStatus(it)
        },
    )
}

@Composable
private fun CartScreenContent(
    state: CartState,
    onProductCardClick: (String) -> Unit,
    onBuyButtonClick: () -> Unit,
    onConfirmButtonClick: (CartItem, Int) -> Unit,
    onDismissButtonClick: (CartItem) -> Unit,
    onFavouriteButtonClick: (Product) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        when {
            state.cartItems.isEmpty() -> {
                Card(Modifier.align(Alignment.Center)) {
                    Text(
                        text = "Try adding some Products to your Cart :)",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(12.dp),
                        fontSize = 45.sp,
                        lineHeight = 65.sp,
                        textAlign = TextAlign.Center,
                        fontStyle = FontStyle.Italic,
                    )
                }
            }
            else -> {
                CartItemColumn(
                    cartItems = state.cartItems,
                    onProductCardClick = onProductCardClick,
                    onConfirmButtonClick = onConfirmButtonClick,
                    onDismissButtonClick = onDismissButtonClick,
                    onFavouriteButtonClick = onFavouriteButtonClick,
                )
                ExtendedFloatingActionButton(
                    modifier =
                        Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
                    onClick = { onBuyButtonClick() },
                    text = { Text(text = "Checkout: %.2f€".format(state.total)) },
                    icon = { Icon(Icons.Filled.CreditCard, "Checkout Button") },
                )
            }
        }
    }
}

@Composable
private fun CartItemColumn(
    cartItems: List<CartItem>,
    onProductCardClick: (String) -> Unit,
    onConfirmButtonClick: (CartItem, Int) -> Unit,
    onDismissButtonClick: (CartItem) -> Unit,
    onFavouriteButtonClick: (Product) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp),
    ) {
        items(items = cartItems) { cartItem ->
            var amountDialogIsOpen by remember { mutableStateOf(false) }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(IntrinsicSize.Min),
            ) {
                ProductCard(
                    product = cartItem.product,
                    onProductCardClick = onProductCardClick,
                    modifier =
                        Modifier
                            .weight(1f)
                            .padding(
                                start = 8.dp,
                                end = 4.dp,
                                top = 4.dp,
                                bottom = 4.dp,
                            ),
                    onFavouriteButtonClick = onFavouriteButtonClick,
                )
                AmountAndButton(
                    cartItem = cartItem,
                    onCancelButtonClick = { amountDialogIsOpen = true },
                )
                AmountDialog(
                    cartItem = cartItem,
                    onDismissRequest = { amountDialogIsOpen = false },
                    onConfirmButtonClick = {
                        onConfirmButtonClick(cartItem, it)
                        amountDialogIsOpen = false
                    },
                    onDismissButtonClick = {
                        onDismissButtonClick(cartItem)
                        amountDialogIsOpen = false
                    },
                    amountDialogIsOpen = amountDialogIsOpen,
                )
            }
        }
    }
}

@Composable
private fun AmountDialog(
    cartItem: CartItem,
    amountDialogIsOpen: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: (Int) -> Unit,
    onDismissButtonClick: () -> Unit,
) {
    if (amountDialogIsOpen) {
        var sliderPosition by remember { mutableFloatStateOf(cartItem.quantity.toFloat()) }
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text("Updating amounts") },
            text = {
                Column {
                    Slider(
                        value = sliderPosition,
                        onValueChange = { sliderPosition = it },
                        colors =
                            SliderDefaults.colors(
                                thumbColor = MaterialTheme.colorScheme.secondary,
                                activeTrackColor = MaterialTheme.colorScheme.secondary,
                                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                            ),
                        steps = 9,
                        valueRange = 0f..10f,
                    )
                    Text(
                        text = sliderPosition.roundToInt().toString(),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onConfirmButtonClick(sliderPosition.roundToInt())
                }) {
                    Text("Change amount")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismissButtonClick() }) {
                    Text("Delete")
                }
            },
            textContentColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
private fun AmountAndButton(
    cartItem: CartItem,
    onCancelButtonClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight(),
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.size(56.dp),
            shape = CircleShape,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("${cartItem.quantity}x")
            }
        }
        IconButton(
            onClick = { onCancelButtonClick() },
            modifier = Modifier.size(56.dp),
        ) {
            Icon(
                imageVector = Icons.Filled.Create,
                contentDescription = "Cancel Button",
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartScreenPreview() {
    MiniShopTheme {
        CartScreenContent(
            CartState(
                cartItems = listOf(CartItem(TestData.testProduct, 10)),
                total = TestData.testProduct.price * 10,
            ),
            onProductCardClick = {},
            onBuyButtonClick = {},
            onConfirmButtonClick = { _, _ -> },
            onDismissButtonClick = {},
            onFavouriteButtonClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyCartScreenPreview() {
    MiniShopTheme {
        CartScreenContent(
            CartState(
                listOf(),
                0F,
            ),
            onProductCardClick = {},
            onBuyButtonClick = {},
            onConfirmButtonClick = { _, _ -> },
            onDismissButtonClick = {},
            onFavouriteButtonClick = {},
        )
    }
}
