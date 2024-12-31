package com.example.wishlistdb

import android.app.Application

class WishListDbApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}