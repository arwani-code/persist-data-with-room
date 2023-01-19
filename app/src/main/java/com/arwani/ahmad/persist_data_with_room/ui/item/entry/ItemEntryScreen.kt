package com.arwani.ahmad.persist_data_with_room.ui.item.entry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arwani.ahmad.persist_data_with_room.InventoryTopAppBar
import com.arwani.ahmad.persist_data_with_room.R
import com.arwani.ahmad.persist_data_with_room.ui.AppViewModelProvider
import com.arwani.ahmad.persist_data_with_room.ui.component.ItemInputForm
import com.arwani.ahmad.persist_data_with_room.ui.item.model.ItemDetails
import com.arwani.ahmad.persist_data_with_room.ui.item.model.ItemUiState
import com.arwani.ahmad.persist_data_with_room.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object ItemEntryDestination : NavigationDestination {
    override val route: String
        get() = "item_entry"
    override val titleRes: Int
        get() = R.string.item_entry_title
}

@Composable
fun ItemEntryScreen(
    navigationBack: () -> Unit,
    onNavigationUp: () -> Unit,
    modifier: Modifier = Modifier,
    canNavigationBack: Boolean = true,
    viewModel: ItemEntryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AppViewModelProvider.factory)
) {

    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
        InventoryTopAppBar(
            title = stringResource(id = ItemEntryDestination.titleRes),
            canNavigationBack = canNavigationBack,
            navigateUp = onNavigationUp
        )
    }) {
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    navigationBack()
                }
            }, modifier = modifier.padding(it)
        )
    }
}

@Composable
fun ItemEntryBody(
    itemUiState: ItemUiState,
    onItemValueChange: (ItemDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        ItemInputForm(itemDetails = itemUiState.itemDetails, onValueChange = onItemValueChange)
        Button(
            onClick = onSaveClick,
            enabled = itemUiState.isEntryValid,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.save_action))
        }
    }
}