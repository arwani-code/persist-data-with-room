package com.arwani.ahmad.persist_data_with_room

import android.app.Application
import com.arwani.ahmad.persist_data_with_room.data.AppContainer
import com.arwani.ahmad.persist_data_with_room.data.AppDataContainer

class InventoryApplication : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}