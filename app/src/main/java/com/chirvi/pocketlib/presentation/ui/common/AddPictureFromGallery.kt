package com.chirvi.pocketlib.presentation.ui.common

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chirvi.pocketlib.R
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme

@Composable
fun AddPictureFromGallery(
    load: () -> Unit,
    image: Uri?,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(80.dp)
            .background(
                color = PocketLibTheme.colors.primary,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable { load() }
    ) {
        if (image != null) {
            Image(
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                modifier = Modifier.size(40.dp),
                tint = PocketLibTheme.colors.onPrimary,
                painter = painterResource(id = R.drawable.add),
                contentDescription = null
            )
        }
    }
}