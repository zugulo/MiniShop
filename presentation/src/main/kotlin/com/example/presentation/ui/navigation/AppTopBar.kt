package com.example.presentation.ui.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.theme.MiniShopTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiniShopAppBar(
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "BackButton",
                )
            }
        },
        title = {
            Text(
                text = "Mini Shop",
                textAlign = TextAlign.Center,
            )
        },
        actions = {
            Spacer(
                modifier = Modifier.width(48.dp),
            )
        },
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.secondary,
            ),
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppBar() {
    MiniShopTheme {
        MiniShopAppBar(
            onBackButtonClick = {},
        )
    }
}
