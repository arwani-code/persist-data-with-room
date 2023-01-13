package com.arwani.ahmad.persist_data_with_room.data

import android.content.Context

interface AppContainer {
    val itemRepository: ItemRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val itemRepository: ItemRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}