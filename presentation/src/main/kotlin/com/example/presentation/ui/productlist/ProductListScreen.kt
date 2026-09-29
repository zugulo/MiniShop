package com.example.presentation.ui.productlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.entity.Product
import com.example.presentation.ui.component.ProductCard
import com.example.presentation.ui.theme.MiniShopTheme
import com.example.test.TestData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductListScreenContent(
        onProductCardClick = onProductClick,
        onSearchButtonClick = {
            viewModel.loadProductsByQuery(it)
        },
        state = state,
        onFavouriteButtonClick = {
            viewModel.changeFavouriteStatus(it)
        },
    )
}

@Composable
private fun ProductListScreenContent(
    state: ProductListState,
    onProductCardClick: (String) -> Unit,
    onSearchButtonClick: (String) -> Unit,
    onFavouriteButtonClick: (Product) -> Unit,
) {
    Column {
        Spacer(Modifier.size(4.dp))
        TopSearchBar(onSearchButtonClick = { onSearchButtonClick(it) })
        when {
            state.isError -> {
                ErrorCard()
            }
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier =
                        Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(12.dp)
                            .size(36.dp),
                )
            }
            state.products.isEmpty() && !state.isLoading -> {
                NoProductsFoundCard()
            }
            else -> {
                ProductColumn(
                    products = state.products,
                    onProductCardClick = onProductCardClick,
                    onFavouriteButtonClick = onFavouriteButtonClick,
                )
            }
        }
    }
}

@Composable
private fun ProductColumn(
    products: List<Product>,
    onProductCardClick: (String) -> Unit,
    onFavouriteButtonClick: (Product) -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                ),
    ) {
        items(items = products) { product ->
            ProductCard(
                product = product,
                onProductCardClick = onProductCardClick,
                modifier =
                    Modifier.padding(
                        horizontal = 8.dp,
                        vertical = 4.dp,
                    ),
                onFavouriteButtonClick = onFavouriteButtonClick,
            )
        }
    }
}

@Composable
private fun NoProductsFoundCard() {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        shape = RoundedCornerShape(28.dp),
    ) {
        Text(
            text = "No Products found. Try a different search",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(12.dp),
            fontSize = 45.sp,
            lineHeight = 65.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun ErrorCard() {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        shape = RoundedCornerShape(28.dp),
    ) {
        Text(
            text = "No internet. Unable to fetch more data",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(12.dp),
            fontSize = 45.sp,
            lineHeight = 65.sp,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
        )
    }
}

@Composable
private fun TopSearchBar(onSearchButtonClick: (String) -> Unit) {
    val textFieldState = rememberTextFieldState("", TextRange(0, 12))
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.size(8.dp))
        OutlinedTextField(
            state = textFieldState,
            placeholder = {
                Text(
                    "Search for Products here",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            },
            lineLimits = TextFieldLineLimits.SingleLine,
            modifier = Modifier.weight(1f),
            colors =
                TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                ),
            shape = MaterialTheme.shapes.large,
        )
        Spacer(Modifier.size(8.dp))
        Button(
            onClick = {
                onSearchButtonClick(textFieldState.text.toString())
            },
            modifier = Modifier.wrapContentWidth(),
        ) {
            Text("Search")
        }
        Spacer(Modifier.size(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenWithProductPreview() {
    MiniShopTheme {
        ProductListScreenContent(
            state =
                ProductListState(
                    products = listOf(TestData.testProduct),
                    isLoading = false,
                    isError = false,
                ),
            onProductCardClick = {},
            onSearchButtonClick = {},
            onFavouriteButtonClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreviewWithoutAndLoadingProducts() {
    MiniShopTheme {
        ProductListScreenContent(
            state =
                ProductListState(
                    products = listOf(),
                    isLoading = true,
                    isError = false,
                ),
            onSearchButtonClick = { },
            onProductCardClick = {},
            onFavouriteButtonClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListScreenPreviewWithoutProductsAndWithError() {
    MiniShopTheme {
        ProductListScreenContent(
            state =
                ProductListState(
                    products = listOf(),
                    isLoading = true,
                    isError = true,
                ),
            onProductCardClick = {},
            onSearchButtonClick = {},
            onFavouriteButtonClick = {},
        )
    }
}

@Preview(showBackground = true)
@PreviewFontScale
@Composable
private fun ProductListScreenPreviewWithoutProductsAndWithOutError() {
    MiniShopTheme {
        ProductListScreenContent(
            state =
                ProductListState(
                    products = listOf(),
                    isLoading = false,
                    isError = false,
                ),
            onProductCardClick = {},
            onSearchButtonClick = {},
            onFavouriteButtonClick = {},
        )
    }
}
