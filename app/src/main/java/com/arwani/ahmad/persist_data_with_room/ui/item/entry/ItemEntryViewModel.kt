package com.arwani.ahmad.persist_data_with_room.ui.item.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.arwani.ahmad.persist_data_with_room.data.ItemRepository
import com.arwani.ahmad.persist_data_with_room.ui.item.model.ItemDetails
import com.arwani.ahmad.persist_data_with_room.ui.item.model.ItemUiState
import com.arwani.ahmad.persist_data_with_room.ui.item.model.toItem

class ItemEntryViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    suspend fun saveItem() {
        if (validateInput()) {
            itemRepository.insertItem(itemUiState.itemDetails.toItem())
        }
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

