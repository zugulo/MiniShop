package com.example.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.domain.entity.Product
import com.example.presentation.ui.theme.MiniShopTheme
import com.example.test.TestData

@Composable
fun ProductCard(
    product: Product,
    onFavouriteButtonClick: (Product) -> Unit,
    onProductCardClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = { onProductCardClick(product.ean) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
    ) {
        Row(
            modifier =
                Modifier
                    .height(145.dp)
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(Modifier.size(4.dp))
            Box(Modifier.size(130.dp)) {
                AsyncImage(
                    model = product.images[0].url,
                    contentDescription = product.images[0].altText,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier.clip(RoundedCornerShape(12.dp)),
                )
            }
            Spacer(Modifier.size(4.dp))
            Text(
                text = product.name,
                modifier =
                    Modifier
                        .weight(1f),
                overflow = TextOverflow.Ellipsis,
                maxLines = 4,
            )
            Spacer(Modifier.size(4.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                IconButton(
                    onClick = { onFavouriteButtonClick(product) },
                    modifier = Modifier.size(48.dp),
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        tint =
                            when (product.isFavourite) {
                                true -> Color.Yellow
                                else -> Color.White
                            },
                        contentDescription = "Favourite Button",
                        modifier = Modifier.size(32.dp),
                    )
                }
                Text("%.2f€".format(product.price))
            }
            Spacer(Modifier.size(4.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ProductCardPreview() {
    MiniShopTheme {
        ProductCard(
            product = TestData.testProduct,
            onFavouriteButtonClick = { },
            onProductCardClick = { },
        )
    }
}
