package ru.kazantsev.eventsandposters.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.kazantsev.eventsandposters.data.database.AppDatabase.Companion.DB_VERSION
import ru.kazantsev.eventsandposters.data.database.dao.EventsDao
import ru.kazantsev.eventsandposters.data.database.dao.PostsDao
import ru.kazantsev.eventsandposters.model.Event
import ru.kazantsev.eventsandposters.model.Post

@Database(
    entities = [Post::class, Event::class],
    version = DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostsDao(): PostsDao

    abstract fun getEventsDao(): EventsDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "app_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}