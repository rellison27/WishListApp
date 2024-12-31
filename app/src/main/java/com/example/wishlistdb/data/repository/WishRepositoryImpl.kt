package com.example.wishlistdb.data.repository

import com.example.wishlistdb.data.model.Wish
import com.example.wishlistdb.data.model.WishDao
import kotlinx.coroutines.flow.Flow

class WishRepositoryImpl(private val wishDao: WishDao) : WishRepository
{
    override suspend fun addWish(wish: Wish) = wishDao.addWish(wish)

    override fun getWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    override fun getWish(id: Long): Flow<Wish> = wishDao.getWish(id)

    override suspend fun updateWish(wish: Wish) = wishDao.updateWish(wish)

    override suspend fun deleteWish(wish: Wish) = wishDao.deleteWish(wish)

}