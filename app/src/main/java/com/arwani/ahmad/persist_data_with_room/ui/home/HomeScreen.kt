package com.arwani.ahmad.persist_data_with_room.ui.home

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arwani.ahmad.persist_data_with_room.R
import com.arwani.ahmad.persist_data_with_room.data.Item
import com.arwani.ahmad.persist_data_with_room.ui.navigation.NavigationDestination
import java.text.NumberFormat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.arwani.ahmad.persist_data_with_room.InventoryTopAppBar
import com.arwani.ahmad.persist_data_with_room.ui.AppViewModelProvider

object HomeDestination : NavigationDestination {
    override val route: String = "home"
    override val titleRes: Int = R.string.app_name
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory)
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(id = HomeDestination.titleRes),
                canNavigationBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                modifier = modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.item_entry_title),
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    ) { innerPadding ->
        HomeBody(
            itemList = homeUiState.itemList,
            onItemClick = navigateToItemUpdate,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun InventoryListHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        headerList.forEach {
            Text(
                text = stringResource(id = it.headerStringId),
                modifier = modifier.weight(it.weight),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
private fun HomeBody(
    modifier: Modifier = Modifier,
    itemList: List<Item>,
    onItemClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InventoryListHeader()
        Divider()
        if (itemList.isEmpty()) {
            Text(
                text = stringResource(id = R.string.no_item_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            InventoryList(itemList = itemList, onItemClick = { onItemClick(it.id) })
        }
    }
}

@Composable
private fun InventoryList(
    modifier: Modifier = Modifier,
    itemList: List<Item>,
    onItemClick: (Item) -> Unit
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = itemList, key = { it.id }) { item ->
            InventoryItem(item = item, onItemClick = onItemClick)
            Divider()
        }
    }
}

@Composable
private fun InventoryItem(
    item: Item,
    onItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onItemClick(item) }
        .padding(16.dp)) {
        Text(text = item.name, modifier = modifier.weight(1.5f), fontWeight = FontWeight.Bold)
        Text(
            text = NumberFormat.getCurrencyInstance().format(item.price),
            modifier = modifier.weight(1.0f)
        )
        Text(text = item.quantity.toString(), modifier = modifier.weight(1.0f))
    }
}

private data class InventoryHeader(@StringRes val headerStringId: Int, val weight: Float)

private val headerList = listOf(
    InventoryHeader(headerStringId = R.string.item, weight = 1.5f),
    InventoryHeader(headerStringId = R.string.price, weight = 1.0f),
    InventoryHeader(headerStringId = R.string.quantity_in_stock, weight = 1.0f),
)