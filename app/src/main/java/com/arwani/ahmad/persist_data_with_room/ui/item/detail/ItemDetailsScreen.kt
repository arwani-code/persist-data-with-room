package com.arwani.ahmad.persist_data_with_room.ui.item.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arwani.ahmad.persist_data_with_room.R
import com.arwani.ahmad.persist_data_with_room.ui.component.ItemInputForm
import com.arwani.ahmad.persist_data_with_room.ui.navigation.NavigationDestination

object ItemDetailsDestination : NavigationDestination {
    override val route: String
        get() = "item_details"
    override val titleRes: Int
        get() = R.string.item_detail_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@Composable
fun ItemDetailsScreen() {

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