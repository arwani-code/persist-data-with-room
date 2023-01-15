package com.arwani.ahmad.persist_data_with_room.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arwani.ahmad.persist_data_with_room.ui.home.HomeDestination
import com.arwani.ahmad.persist_data_with_room.ui.home.HomeScreen
import com.arwani.ahmad.persist_data_with_room.ui.item.detail.ItemDetailsDestination
import com.arwani.ahmad.persist_data_with_room.ui.item.entry.ItemEntryDestination

@Composable
fun InventoryNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                })
        }
    }
}