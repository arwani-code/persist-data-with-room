package com.arwani.ahmad.persist_data_with_room.ui.item.model


data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)