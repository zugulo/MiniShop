package com.example.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.presentation.ui.theme.MiniShopTheme

@Composable
fun HomeScreen(
    onProductButtonClick: () -> Unit,
    onCartButtonClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(
        onProductButtonClick = onProductButtonClick,
        onCartButtonClick = onCartButtonClick,
    )
}

@Composable
private fun HomeScreenContent(
    onProductButtonClick: () -> Unit,
    onCartButtonClick: () -> Unit,
) {
    Column {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(140.dp),
        ) {
            AsyncImage(
                model =
                    "",//imagine a banner here
                contentDescription = "banner",
                contentScale = ContentScale.FillBounds,
            )
        }
        Button(
            onClick = onProductButtonClick,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
        ) {
            Text("Products")
        }
        Button(
            onClick = onCartButtonClick,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
        ) {
            Text("Cart")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MiniShopTheme {
        HomeScreenContent(
            onProductButtonClick = {},
            onCartButtonClick = {},
        )
    }
}
