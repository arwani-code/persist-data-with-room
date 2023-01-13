package com.arwani.ahmad.persist_data_with_room.data

import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getAllItemStream(): Flow<List<Item>>

    fun getItemStream(id: Int): Flow<Item?>

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun update(item: Item)
}