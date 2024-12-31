package com.example.wishlistdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish_table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish_title")
    val title: String = "",
    @ColumnInfo(name = "wish_desc")
    val description: String = ""
)

object DummyWish {
    val wishList = listOf(
        Wish(
            title = "Macbook pro with M10",
            description = "That thang crazy it moves so fast"
        ),
        Wish(
            title = "Macbook pro with M10",
            description = "That thang crazy it moves so fast"
        ),
        Wish(
            title = "Macbook pro with M10",
            description = "That thang crazy it moves so fast"
        ),
        Wish(
            title = "Macbook pro with M10",
            description = "That thang crazy it moves so fast"
        ),
        Wish(
            title = "Macbook pro with M10",
            description = "That thang crazy it moves so fast"
        )
    )
}
