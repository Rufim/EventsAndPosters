package ru.kazantsev.eventsandposters.data.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import ru.kazantsev.eventsandposters.data.repository.PostsRepository
import ru.kazantsev.eventsandposters.model.Post
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) : BaseSingleUseCase<Unit, List<Post>>() {

    override fun run(param: Unit): Flow<List<Post>> {
        return postsRepository.getAllPosts()
    }
}