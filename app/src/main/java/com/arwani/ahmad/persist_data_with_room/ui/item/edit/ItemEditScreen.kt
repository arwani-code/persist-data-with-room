package com.arwani.ahmad.persist_data_with_room.ui.item.edit

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.arwani.ahmad.persist_data_with_room.InventoryTopAppBar
import com.arwani.ahmad.persist_data_with_room.R
import com.arwani.ahmad.persist_data_with_room.ui.AppViewModelProvider
import com.arwani.ahmad.persist_data_with_room.ui.item.entry.ItemEntryBody
import com.arwani.ahmad.persist_data_with_room.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object ItemEditDestination : NavigationDestination {
    override val route: String
        get() = "item_edit"
    override val titleRes: Int
        get() = R.string.edit_item_title
    const val itemIdArg = "itemId"
    val routingWithArgs = "$route/{$itemIdArg}"
}

@Composable
fun ItemEditScreen(
    modifier: Modifier = Modifier,
    navigationBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: ItemEditViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        InventoryTopAppBar(
            title = stringResource(id = ItemEditDestination.titleRes),
            canNavigationBack = true,
            navigateUp = onNavigateUp
        )
    }) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateItem()
                    navigationBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}