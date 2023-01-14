package com.arwani.ahmad.persist_data_with_room.ui.item

import androidx.compose.runtime.Composable
import com.arwani.ahmad.persist_data_with_room.R
import com.arwani.ahmad.persist_data_with_room.ui.NavigationDestination

object ItemDetailsDestination : NavigationDestination {
    override val route: String
        get() = "item_details"
    override val titleRes: Int
        get() = R.string.item_detail_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@Composable
fun ItemDetailsScreen(){

}