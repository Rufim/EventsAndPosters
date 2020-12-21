package ru.kazantsev.eventsandposters.ui

import android.view.View
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

const val ISO_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun convertDate(date: String) : String {
    val inFormat = SimpleDateFormat(ISO_TIME_FORMAT, Locale.getDefault())
    val outFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    return outFormat.format(inFormat.parse(date)!!)
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(invisible: Boolean = false) {
    visibility = if (invisible) View.INVISIBLE else View.GONE
}