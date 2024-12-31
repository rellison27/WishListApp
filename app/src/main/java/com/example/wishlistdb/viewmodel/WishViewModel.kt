package com.example.wishlistdb.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistdb.Graph
import com.example.wishlistdb.data.model.Wish
import com.example.wishlistdb.data.repository.WishRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val  wishRepository: WishRepositoryImpl = Graph.wishRepository
): ViewModel()
{

    private val _selectedWish = mutableStateOf(Wish())
    lateinit var wishes: Flow<List<Wish>>
    var wishTitle by mutableStateOf("")
    var wishDescription by mutableStateOf("")

    init {
        viewModelScope.launch {
            wishes = wishRepository.getWishes()
        }
    }

    var selectedWish = _selectedWish

    fun updateWish(wish: Wish)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            wishRepository.updateWish(wish)
        }
    }

    fun addWish(wish: Wish)
    {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }

    fun getWish(id: Long) : Flow<Wish>
    {
        return wishRepository.getWish(id)
    }

    fun deleteWish(wish: Wish)
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            wishRepository.deleteWish(wish = wish)
        }
    }

    fun onWishTitleChanged(newTitle: String)
    {
        wishTitle = newTitle
    }

    fun onWishDescriptionChanged(newDescription: String)
    {
        wishDescription = newDescription
    }

    // Old way want to figure out why it wasn't working
    fun updateTitle(newTitle: String)
    {
        _selectedWish.value = _selectedWish.value.copy(
            title = newTitle,
            description = selectedWish.value.description
        )
    }

    fun updateDescription(newDescription: String)
    {
        _selectedWish.value = _selectedWish.value.copy(
            title = selectedWish.value.title,
            description = newDescription
        )
    }
}