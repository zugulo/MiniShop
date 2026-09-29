package com.example.presentation.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.ui.cart.CartScreen
import com.example.presentation.ui.home.HomeScreen
import com.example.presentation.ui.productdetail.ProductDetailScreen
import com.example.presentation.ui.productlist.ProductListScreen

@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            MiniShopAppBar(
                onBackButtonClick = { navController.popBackStack() },
            )
        },
        bottomBar = {
            NavBar(navController = navController)
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Home.route,
            modifier =
                Modifier
                    .padding(innerPadding),
        ) {
            composable(route = Routes.Home.route) {
                HomeScreen(
                    onProductButtonClick = { navController.navigate(Routes.List.route) },
                    onCartButtonClick = { navController.navigate(Routes.Cart.route) },
                )
            }
            composable(route = Routes.List.route) {
                ProductListScreen(
                    onProductClick = { productEan ->
                        navController.navigate(
                            Routes.Product.createRoute(productEan),
                        )
                    },
                )
            }
            composable(route = Routes.Cart.route) {
                CartScreen(
                    onProductCardClick = { ean ->
                        navController.navigate(
                            Routes.Product.createRoute(ean),
                        )
                    },
                    onBuyButtonClick = { /* not implemented yet*/ },
                )
            }
            composable(
                route = Routes.Product.route,
                arguments =
                    listOf(
                        navArgument("ean") {
                            type = NavType.StringType
                        },
                    ),
            ) {
                ProductDetailScreen(
                    onAddProductToCartClick = { navController.popBackStack() },
                )
            }
        }
    }
}
