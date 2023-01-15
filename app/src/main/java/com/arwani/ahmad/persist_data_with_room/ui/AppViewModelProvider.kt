package com.arwani.ahmad.persist_data_with_room.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.arwani.ahmad.persist_data_with_room.InventoryApplication
import com.arwani.ahmad.persist_data_with_room.ui.home.HomeViewModel
import com.arwani.ahmad.persist_data_with_room.ui.item.detail.ItemDetailsViewModel
import com.arwani.ahmad.persist_data_with_room.ui.item.edit.ItemEditViewModel
import com.arwani.ahmad.persist_data_with_room.ui.item.entry.ItemEntryViewModel

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemRepository
            )
        }

        initializer {
            ItemEntryViewModel(inventoryApplication().container.itemRepository)
        }

        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemRepository
            )
        }

        initializer {
            HomeViewModel(inventoryApplication().container.itemRepository)
        }
    }
}

fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)