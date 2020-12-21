package ru.kazantsev.eventsandposters.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kazantsev.eventsandposters.model.Event.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Event(
    @PrimaryKey override val id: Int,
    val event_datetime: String,
    val location: String,
    val description: String? = null
) : Idable {
    companion object {
        const val TABLE_NAME = "events"
    }
}