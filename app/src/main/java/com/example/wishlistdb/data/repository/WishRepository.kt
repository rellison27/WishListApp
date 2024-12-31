package com.example.wishlistdb.data.repository

import com.example.wishlistdb.data.model.Wish
import com.example.wishlistdb.data.model.WishDao
import kotlinx.coroutines.flow.Flow

interface WishRepository
{
    suspend fun addWish(wish: Wish)

    fun getWishes(): Flow<List<Wish>>

    fun getWish(id: Long): Flow<Wish>

    suspend fun updateWish(wish: Wish)

    suspend fun deleteWish(wish: Wish)
}