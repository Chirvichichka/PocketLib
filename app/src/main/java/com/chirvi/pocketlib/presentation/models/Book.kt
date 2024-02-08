package com.chirvi.pocketlib.presentation.models

import com.chirvi.pocketlib.R

data class Book(
    val id: Int = 0,
    val posterId: Int = R.drawable.test_image,
    val name: String = "Название",
    val author: String = "Автор"
)