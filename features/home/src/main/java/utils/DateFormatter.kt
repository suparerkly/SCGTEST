package utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun getDateFormatter(date: String): String {
    val formatter = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd'T'HH:mm:ss'Z'",
        Locale.ENGLISH
    )
    val dateTime =
        LocalDateTime.parse(date, formatter)
    var formatterOutputFirst =
        DateTimeFormatter.ofPattern("MMM dd, ")
    var formatterOutputSecond =
        DateTimeFormatter.ofPattern("HH:mm")


    return formatterOutputFirst.format(dateTime) + formatterOutputSecond.format(dateTime)
}
