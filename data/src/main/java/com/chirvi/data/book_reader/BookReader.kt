package com.chirvi.data.book_reader

import android.content.Context
import org.jsoup.Jsoup
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipFile

class BookReader(private val context: Context) {
    suspend fun create(id: String): List<String> {

        val downloader = BookDownloader(context)
        val downloadedFile = downloader.downloadBook(id) ?: return emptyList()

        val outputDirectoryName = "book_reader/content_$id"
        val outputDirectory = File(context.filesDir, outputDirectoryName)

        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs()
        }

        val zipFile = ZipFile(downloadedFile)
        val entries = zipFile.entries()

        // Проходимся по всем файлам в EPUB и распаковываем их во внутреннюю директорию
        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            val entryName = entry.name

            if (!entry.isDirectory) {
                val entryInputStream = zipFile.getInputStream(entry)
                val outputFile = File(outputDirectory, entryName)

                val outputParentDir = outputFile.parentFile
                if (!outputParentDir.exists()) {
                    outputParentDir.mkdirs()
                }

                val outputStream = FileOutputStream(outputFile)
                entryInputStream.copyTo(outputStream)

                outputStream.close()
                entryInputStream.close()

                outputFile.deleteOnExit()
            }
        }

        zipFile.close()
        // Удаляем временный файл EPUB
        downloadedFile.delete()

        return readXhtml(outputDirectory)
    }

    private fun readXhtml(directory: File): List<String> {
        val textList = mutableListOf<String>()
        val htmlFiles = mutableListOf<File>()

        // Рекурсивная функция для обхода папок и чтения файлов
        fun exploreDirectory(directory: File) {
            val files = directory.listFiles()
            if (files != null) {
                // Сортируем файлы по именам
                val sortedFiles = files.sortedBy { it.name }

                for (file in sortedFiles) {
                    if (file.isDirectory) {
                        // Если это папка, рекурсивно вызываем эту же функцию для нее
                        exploreDirectory(file)
                    }else if (file.isFile &&
                        (
                            file.extension.equals("html", ignoreCase = true) ||
                            file.extension.equals("xhtml", ignoreCase = true))
                        ) {
                        // Если это файл с расширением .html или .xhtml, добавляем его в список
                        htmlFiles.add(file)
                    }
                }
            }
        }

        // Если папка существует, начинаем обход
        exploreDirectory(directory)

        // Читаем содержимое всех найденных файлов .xhtml
        for (xhtmlFile in htmlFiles) {
            val text = extractTextFromXHTML(xhtmlFile)
            textList.add(text)
        }
        directory.delete()
        return textList
    }

    private fun extractTextFromXHTML(file: File): String {
        val document = Jsoup.parse(file, "UTF-8")
        val stringBuilder = StringBuilder()
        document.body().children().forEach { element ->
            when (element.tagName()) {
                "p", "h1", "h2", "h3", "h4", "h5", "h6", "br", "hr", "ul", "li", "div", "a" -> stringBuilder.appendLine(element.text())
                else -> stringBuilder.appendLine(element.text())
            }
        }
        return stringBuilder.toString()
    }
}

