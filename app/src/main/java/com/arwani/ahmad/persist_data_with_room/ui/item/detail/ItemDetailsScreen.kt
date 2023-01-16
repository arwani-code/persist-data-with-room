package com.arwani.ahmad.persist_data_with_room.ui.item.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arwani.ahmad.persist_data_with_room.R
import com.arwani.ahmad.persist_data_with_room.ui.component.ItemInputForm
import com.arwani.ahmad.persist_data_with_room.ui.navigation.NavigationDestination
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arwani.ahmad.persist_data_with_room.InventoryTopAppBar
import com.arwani.ahmad.persist_data_with_room.ui.AppViewModelProvider
import kotlinx.coroutines.launch

object ItemDetailsDestination : NavigationDestination {
    override val route: String
        get() = "item_details"
    override val titleRes: Int
        get() = R.string.item_detail_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@Composable
fun ItemDetailsScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemDetailsViewModel = viewModel(factory = AppViewModelProvider.factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ItemDetailsDestination.titleRes),
                canNavigationBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditItem(uiState.value.itemDetails.id) },
                modifier = modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(id = R.string.edit_item_title),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    ) { innerPadding ->
        ItemDetailsBody(
            itemDetailUiState = uiState.value,
            onSellItem = { viewModel.reduceQuantityByOne() },
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigateBack()
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun ItemDetailsBody(
    itemDetailUiState: ItemDetailUiState,
    onSellItem: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var deleteConfirmationRequired by remember {
            mutableStateOf(false)
        }
        ItemInputForm(itemDetails = itemDetailUiState.itemDetails, enabled = false)
        Button(
            onClick = onSellItem,
            modifier = modifier.fillMaxWidth(),
            enabled = !itemDetailUiState.outOfStock
        ) {
            Text(text = stringResource(id = R.string.sell))
        }
        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.delete))
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDelete()
            }, onDeleteCancel = { deleteConfirmationRequired = false })
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        title = { Text(text = stringResource(id = R.string.attention)) },
        text = {
            Text(
                text = stringResource(id = R.string.delete_question)
            )
        }, modifier = modifier.padding(16.dp), dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(id = R.string.no))
            }
        }, confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(id = R.string.yes))
            }
        })
}