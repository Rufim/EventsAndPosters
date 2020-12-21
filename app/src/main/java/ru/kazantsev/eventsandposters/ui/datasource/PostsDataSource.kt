package ru.kazantsev.eventsandposters.ui.datasource

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.data.usecase.GetPostsUseCase
import ru.kazantsev.eventsandposters.model.Post
import ru.kazantsev.eventsandposters.ui.viewmodel.PostsViewModel

@ExperimentalCoroutinesApi
class PostsDataSource(
    viewModel: PostsViewModel,
    postsUseCase: GetPostsUseCase,
    progressNotifier: ProgressNotifierCallback? = null
) : SimpleDataSource<Unit, Post>(viewModel, postsUseCase, progressNotifier) {


    override fun getParamByKey(key: Int?) = Unit

}