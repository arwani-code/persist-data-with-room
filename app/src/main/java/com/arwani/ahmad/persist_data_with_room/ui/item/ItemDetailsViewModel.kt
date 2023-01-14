package com.arwani.ahmad.persist_data_with_room.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arwani.ahmad.persist_data_with_room.data.Item
import com.arwani.ahmad.persist_data_with_room.data.ItemRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    val uiState: StateFlow<ItemDetailUiState> =
        itemRepository.getItemStream(itemId)
            .filterNotNull()
            .map {
                ItemDetailUiState(outOfStock = it.quantity <= 0, itemDetails = it.toItemDetails())
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailUiState()
            )

    fun reduceQuantityByOne() {
        viewModelScope.launch {
            val currentItem = uiState.value.itemDetails.toItem()
            if (currentItem.quantity > 0) {
                itemRepository.update(currentItem.copy(quantity = currentItem.quantity - 1))
            }
        }
    }

    suspend fun deleteItem() {
        itemRepository.deleteItem(uiState.value.itemDetails.toItem())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemDetailUiState(
    val outOfStock: Boolean = true,
    val itemDetails: ItemDetails = ItemDetails()
)