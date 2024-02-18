package com.chirvi.pocketlib.presentation.navigation.item

import com.chirvi.pocketlib.R

sealed class ProfileTabRowItem(
    val title: Int,
    val iconId: Int,
) {
    data object MyBooks : ProfileTabRowItem(
        title = R.string.my_books,
        iconId = R.drawable.library_add
    )
    data object Favorite : ProfileTabRowItem(
        title = R.string.favorite,
        iconId = R.drawable.favorites
    )
}