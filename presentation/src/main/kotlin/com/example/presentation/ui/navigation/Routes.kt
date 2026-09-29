package com.example.presentation.ui.navigation

sealed class Routes(
    val route: String,
    val displayName: String,
) {
    object Home : Routes("home", "Home")

    object List : Routes("products", "Products")

    object Cart : Routes("cart", "Cart")

    object Product : Routes("product/{ean}", "Product")

    fun createRoute(ean: String): String = "product/$ean"
}
