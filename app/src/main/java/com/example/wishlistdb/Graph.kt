package com.example.wishlistdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.wishlistdb.data.model.WishDatabase
import com.example.wishlistdb.data.repository.WishRepositoryImpl

object Graph
{
    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepositoryImpl(wishDao = database.wishDao())
    }

    fun provide(context: Context)
    {
        database = Room.databaseBuilder(
            context = context,
            WishDatabase::class.java,
            "wishlist.db").build()
    }
}