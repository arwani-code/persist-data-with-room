package com.arwani.ahmad.persist_data_with_room.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arwani.ahmad.persist_data_with_room.R
import com.arwani.ahmad.persist_data_with_room.ui.NavigationDestination

object HomeNavigation : NavigationDestination {
    override val route: String
        get() = "home"
    override val titleRes: Int
        get() = R.string.app_name
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier){

}