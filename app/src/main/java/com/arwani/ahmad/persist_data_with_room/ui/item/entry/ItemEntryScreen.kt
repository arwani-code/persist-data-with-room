package com.arwani.ahmad.persist_data_with_room.ui.item.entry

import androidx.compose.runtime.Composable
import com.arwani.ahmad.persist_data_with_room.R
import com.arwani.ahmad.persist_data_with_room.ui.navigation.NavigationDestination

object ItemEntryDestination : NavigationDestination {
    override val route: String
        get() = "item_entry"
    override val titleRes: Int
        get() = R.string.item_entry_title
}

@Composable
fun ItemEntryScreen(){

}