package ru.kazantsev.eventsandposters.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.kazantsev.eventsandposters.model.Event

@Dao
interface EventsDao {

    /**
     * Inserts [posts] into the [Event.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param posts Events
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(posts: List<Event>)

    /**
     * Deletes all the posts from the [Event.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${Event.TABLE_NAME}")
    fun deleteAllEvents()

    /**
     * Fetches the post from the [Event.TABLE_NAME] table whose id is [eventId].
     * @param eventId Unique ID of [Event]
     * @return [Flow] of [Event] from database table.
     */
    @Query("SELECT * FROM ${Event.TABLE_NAME} WHERE ID = :eventId")
    fun getEventById(eventId: Int): Flow<Event?>

    /**
     * Fetches all the posts from the [Event.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Event.TABLE_NAME}")
    fun getAllEvents(): Flow<List<Event>>

}