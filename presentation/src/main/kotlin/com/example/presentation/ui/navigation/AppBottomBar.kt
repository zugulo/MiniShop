package com.example.presentation.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.R
import com.example.presentation.ui.theme.MiniShopTheme
import com.example.presentation.ui.theme.myNavBarColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar(
        windowInsets = NavigationBarDefaults.windowInsets,
        containerColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        NavigationBarItem(
            selected = currentRoute == Routes.Home.route,
            onClick = {
                navController.navigate(route = Routes.Home.route)
            },
            icon = {
                Icon(
                    painterResource(R.drawable.home),
                    contentDescription = "homeButton",
                )
            },
            label = { Text(Routes.Home.displayName) },
            colors = myNavBarColors,
        )
        NavigationBarItem(
            selected = currentRoute == Routes.List.route || currentRoute == Routes.Product.route,
            onClick = {
                navController.navigate(route = Routes.List.route)
            },
            icon = {
                Icon(
                    painterResource(R.drawable.products),
                    contentDescription = "Products",
                )
            },
            label = { Text(Routes.List.displayName) },
            colors = myNavBarColors,
        )
        NavigationBarItem(
            selected = currentRoute == Routes.Cart.route,
            onClick = {
                navController.navigate(route = Routes.Cart.route)
            },
            icon = {
                Icon(
                    painterResource(R.drawable.cart),
                    contentDescription = "Cart",
                )
            },
            label = { Text(Routes.Cart.displayName) },
            colors = myNavBarColors,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNavBar() {
    MiniShopTheme {
        NavBar(rememberNavController())
    }
}
