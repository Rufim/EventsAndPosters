package ru.kazantsev.eventsandposters.data.repository

import androidx.annotation.MainThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.kazantsev.eventsandposters.data.api.ApiService
import ru.kazantsev.eventsandposters.data.database.dao.PostsDao
import ru.kazantsev.eventsandposters.model.Post
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class PostsRepository @Inject constructor(
    private val postsDao: PostsDao,
    private val apiService: ApiService
) {

    fun getAllPosts() = flow<List<Post>> {
        try {
            val apiResponse = apiService.getPosts()
            val posts = apiResponse.body()
            if (apiResponse.isSuccessful && posts != null) {
                // Save posts into the persistence storage
                postsDao.deleteAllPosts()
                postsDao.insertPosts(posts)
                emit(posts)
                return@flow
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        emitAll(postsDao.getAllPosts())
    }


    @MainThread
    fun getPostById(postId: Int): Flow<Post?> = postsDao.getPostById(postId)
}