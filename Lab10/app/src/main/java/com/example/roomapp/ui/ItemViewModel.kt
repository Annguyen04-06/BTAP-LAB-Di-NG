package com.example.roomapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.Item
import com.example.roomapp.data.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ItemUiState(
    val items: List<Item> = emptyList(),
    val itemName: String = "",
    val itemDescription: String = "",
    val itemQuantity: String = "",
    val isEditing: Boolean = false,
    val editingItemId: Int? = null,
    val selectedItem: Item? = null
)

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ItemUiState())
    val uiState: StateFlow<ItemUiState> = _uiState.asStateFlow()
    
    init {
        viewModelScope.launch {
            itemRepository.getAllItemsStream().collect { items ->
                _uiState.update { it.copy(items = items) }
            }
        }
    }
    
    fun updateItemName(name: String) {
        _uiState.update { it.copy(itemName = name) }
    }
    
    fun updateItemDescription(description: String) {
        _uiState.update { it.copy(itemDescription = description) }
    }
    
    fun updateItemQuantity(quantity: String) {
        _uiState.update { it.copy(itemQuantity = quantity) }
    }
    
    fun addItem() {
        if (_uiState.value.itemName.isNotBlank()) {
            val quantity = _uiState.value.itemQuantity.toIntOrNull() ?: 0
            val item = Item(
                name = _uiState.value.itemName,
                description = _uiState.value.itemDescription,
                quantity = quantity
            )
            viewModelScope.launch {
                itemRepository.insertItem(item)
                clearInputs()
            }
        }
    }
    
    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemRepository.deleteItem(item)
            if (_uiState.value.isEditing && _uiState.value.editingItemId == item.id) {
                clearInputs()
            }
        }
    }
    
    fun startEditItem(item: Item) {
        _uiState.update {
            it.copy(
                isEditing = true,
                editingItemId = item.id,
                itemName = item.name,
                itemDescription = item.description,
                itemQuantity = item.quantity.toString(),
                selectedItem = item
            )
        }
    }
    
    fun updateItem() {
        if (_uiState.value.itemName.isNotBlank() && _uiState.value.editingItemId != null) {
            val quantity = _uiState.value.itemQuantity.toIntOrNull() ?: 0
            val updatedItem = Item(
                id = _uiState.value.editingItemId!!,
                name = _uiState.value.itemName,
                description = _uiState.value.itemDescription,
                quantity = quantity
            )
            viewModelScope.launch {
                itemRepository.updateItem(updatedItem)
                clearInputs()
            }
        }
    }
    
    fun cancelEdit() {
        clearInputs()
    }
    
    private fun clearInputs() {
        _uiState.update {
            it.copy(
                itemName = "",
                itemDescription = "",
                itemQuantity = "",
                isEditing = false,
                editingItemId = null,
                selectedItem = null
            )
        }
    }
    
    fun saveItem() {
        if (_uiState.value.isEditing) {
            updateItem()
        } else {
            addItem()
        }
    }
}
