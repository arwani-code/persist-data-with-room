package com.arwani.ahmad.persist_data_with_room.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arwani.ahmad.persist_data_with_room.data.Item
import com.arwani.ahmad.persist_data_with_room.data.ItemRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(itemRepository: ItemRepository) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        itemRepository.getAllItemStream().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HomeUiState(val itemList: List<Item> = listOf())