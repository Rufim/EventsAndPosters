package ru.kazantsev.eventsandposters.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kazantsev.eventsandposters.model.Post.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class Post(
    @PrimaryKey override val id: Int,
    val comment : String,
    val published_at: String,
    val links_to: String? = null,
    val link_caption: String? = null
) : Idable {
    companion object {
        const val TABLE_NAME = "posts"
    }
}