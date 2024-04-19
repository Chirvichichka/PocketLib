package com.chirvi.pocketlib.presentation.ui.screen.main.common.book_page

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.chirvi.pocketlib.presentation.ui.common.LoadingCircle
import com.chirvi.pocketlib.presentation.ui.theme.PocketLibTheme
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File

@Composable
fun BookViewer(id: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PocketLibTheme.colors.background)
    ) {
        PdfViewer(context = LocalContext.current, id = id)
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun PdfViewer(context: Context, id: String) {
    val pdfBitmaps = remember {
        mutableListOf<Bitmap>()
    }
    var isLoading by remember { mutableStateOf(true) }
    val storage = Firebase.storage
    val bookStorageReference = storage.getReference("book/")
    val pdfRef = bookStorageReference.child("$id.pdf")

    SideEffect {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val localFile = File.createTempFile("temp_pdf", "pdf", context.cacheDir)
                pdfRef.getFile(localFile).await()

                val parcelFileDescriptor =
                    ParcelFileDescriptor.open(localFile, ParcelFileDescriptor.MODE_READ_ONLY)
                val pdfRenderer = PdfRenderer(parcelFileDescriptor)
                val pageCount = pdfRenderer.pageCount

                for (i in 0 until pageCount) {
                    val page = pdfRenderer.openPage(i)
                    val bitmap =
                        Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)

                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
                    pdfBitmaps.add(bitmap)
                    page.close()
                }

                pdfRenderer.close()
                parcelFileDescriptor.close()

                isLoading = false
            } catch (e: Exception) {
                null
            }
        }
    }
    if (isLoading) {
        LoadingCircle()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            pdfBitmaps.forEach { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }
    }
}