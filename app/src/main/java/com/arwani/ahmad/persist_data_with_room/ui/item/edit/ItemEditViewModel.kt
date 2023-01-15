package com.arwani.ahmad.persist_data_with_room.ui.item.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arwani.ahmad.persist_data_with_room.data.ItemRepository
import com.arwani.ahmad.persist_data_with_room.ui.item.detail.ItemDetailsDestination
import com.arwani.ahmad.persist_data_with_room.ui.item.model.*
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemRepository: ItemRepository
) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    init {
        viewModelScope.launch {
            itemUiState = itemRepository.getItemStream(itemId)
                .filterNotNull()
                .first()
                .toItemUiState(true)
        }
    }

    suspend fun updateItem() {
        if (validateInput(itemUiState.itemDetails)) {
            itemRepository.update(itemUiState.itemDetails.toItem())
        }
    }

    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean =
        with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
}