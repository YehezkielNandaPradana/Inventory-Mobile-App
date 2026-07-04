package io.github.ComposeKit2.Helper.FormatTanggal

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toDateIndonesia(): String {
    return try {
        val date = LocalDateTime.parse(this)

        date.format(
            DateTimeFormatter.ofPattern(
                "dd MMM yyyy",
                Locale("id", "ID")
            )
        )
    } catch (e: Exception) {
        this
    }
}