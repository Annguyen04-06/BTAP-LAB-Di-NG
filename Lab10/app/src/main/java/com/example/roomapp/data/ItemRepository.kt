package com.example.roomapp.data

import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {
    
    fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()
    
    fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)
    
    suspend fun insertItem(item: Item) {
        itemDao.insert(item)
    }
    
    suspend fun deleteItem(item: Item) {
        itemDao.delete(item)
    }
    
    suspend fun updateItem(item: Item) {
        itemDao.update(item)
    }
}
