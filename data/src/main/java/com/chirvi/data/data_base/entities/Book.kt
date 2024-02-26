package com.chirvi.data.data_base.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val author: String,
    val path: String,
)
