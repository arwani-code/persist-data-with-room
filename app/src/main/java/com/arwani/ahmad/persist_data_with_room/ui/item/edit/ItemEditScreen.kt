package com.arwani.ahmad.persist_data_with_room.ui.item.edit

import androidx.compose.runtime.Composable
import com.arwani.ahmad.persist_data_with_room.R
import com.arwani.ahmad.persist_data_with_room.ui.navigation.NavigationDestination

object ItemEditDestination : NavigationDestination {
    override val route: String
        get() = "item_edit"
    override val titleRes: Int
        get() = R.string.edit_item_title
    const val itemIdArg = "itemId"
    val routingWithArgs = "$route/{$itemIdArg}"
}

@Composable
fun ItemEditScreen(){

}