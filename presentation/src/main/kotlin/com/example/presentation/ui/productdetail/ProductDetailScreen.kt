package com.example.presentation.ui.productdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.domain.entity.Product
import com.example.presentation.R
import com.example.presentation.ui.theme.MiniShopTheme
import com.example.test.TestData
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onAddProductToCartClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProductDetailScreenContent(
        state = state,
        onConfirmButtonClick = { product, quantity ->
            viewModel.addProductToCart(product, quantity)
            onAddProductToCartClick()
        },
    )
}

@Composable
private fun ProductDetailScreenContent(
    state: ProductDetailState,
    onConfirmButtonClick: (Product, Int) -> Unit,
) {
    var amountDialogIsOpen by remember { mutableStateOf(false) }
    when {
        state.product == null -> {
            NoProductFoundCard()
        }
        amountDialogIsOpen -> {
            AmountDialog(
                onDismissRequest = { amountDialogIsOpen = false },
                onConfirmButtonClick = {
                    onConfirmButtonClick(state.product, it)
                    amountDialogIsOpen = false
                },
            )
        }
        else -> {
            ProductDetailColumn(
                state = state,
                onAddProductToCartClick = { amountDialogIsOpen = true },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductDetailColumn(
    state: ProductDetailState,
    onAddProductToCartClick: () -> Unit,
) {
    val product = state.product!!
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
    ) {
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        ) {
            HorizontalMultiBrowseCarousel(
                state = rememberCarouselState { product.images.count() },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                itemSpacing = 8.dp,
                preferredItemWidth = 400.dp,
            ) {
                AsyncImage(
                    model = product.images[it].url,
                    contentDescription = product.images[it].altText,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .clip(shape = RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                            .height(350.dp),
                )
            }
        }
        Text(
            text = product.name,
            fontSize = 35.sp,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Card(
            colors =
                CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                ),
            modifier = Modifier.padding(8.dp),
        ) {
            Text(
                text = "%.2f€".format(product.price),
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Button(
            onClick = onAddProductToCartClick,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
        ) {
            Text(stringResource(R.string.add))
        }
    }
}

@Composable
private fun AmountDialog(
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: (Int) -> Unit,
) {
    var sliderPosition by remember { mutableFloatStateOf(1f) }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Adding products to cart") },
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
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Cancel")
            }
        },
        textContentColor = MaterialTheme.colorScheme.secondary,
        containerColor = MaterialTheme.colorScheme.onPrimary,
        titleContentColor = MaterialTheme.colorScheme.primary,
    )
}

@Composable
private fun NoProductFoundCard() {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
        shape = RoundedCornerShape(28.dp),
    ) {
        Text(
            text = "No Product found. How did you end up here",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(12.dp),
            fontSize = 45.sp,
            lineHeight = 65.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailScreenContentPreview() {
    MiniShopTheme {
        ProductDetailScreenContent(
            state = ProductDetailState(TestData.testProduct),
            onConfirmButtonClick = { _, _ -> },
        )
    }
}
