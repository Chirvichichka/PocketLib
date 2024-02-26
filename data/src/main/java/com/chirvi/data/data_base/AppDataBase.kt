package com.chirvi.data.data_base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chirvi.data.data_base.dao.BookDao
import com.chirvi.data.data_base.entities.Book

@Database(
    entities = [Book::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}