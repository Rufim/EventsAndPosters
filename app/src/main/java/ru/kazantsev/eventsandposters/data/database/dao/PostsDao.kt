package ru.kazantsev.eventsandposters.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.kazantsev.eventsandposters.model.Post

@Dao
interface PostsDao {

    /**
     * Inserts [posts] into the [Post.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param posts Posts
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>)

    /**
     * Deletes all the posts from the [Post.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${Post.TABLE_NAME}")
    fun deleteAllPosts()

    /**
     * Fetches the post from the [Post.TABLE_NAME] table whose id is [postId].
     * @param postId Unique ID of [Post]
     * @return [Flow] of [Post] from database table.
     */
    @Query("SELECT * FROM ${Post.TABLE_NAME} WHERE ID = :postId")
    fun getPostById(postId: Int): Flow<Post?>

    /**
     * Fetches all the posts from the [Post.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Post.TABLE_NAME}")
    fun getAllPosts(): Flow<List<Post>>

}