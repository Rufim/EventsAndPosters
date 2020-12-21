package ru.kazantsev.eventsandposters.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.data.usecase.GetPostsUseCase
import ru.kazantsev.eventsandposters.model.Post
import ru.kazantsev.eventsandposters.ui.datasource.PostsDataSource
import ru.kazantsev.eventsandposters.ui.datasource.ProgressNotifierCallback
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PostsViewModel  @Inject constructor(private val postsUseCase: GetPostsUseCase) : NavigationViewModel() {

    private val pagedPostLiveInternal: MediatorLiveData<PagedList<Post>> = MediatorLiveData()
    val pagedPostLive = pagedPostLiveInternal as LiveData<PagedList<Post>>
    private var eventDataSource: LiveData<PagedList<Post>>? = null


    fun load(progressNotifier: ProgressNotifierCallback? = null) {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        buildLivePagedList(config, progressNotifier).let {
            pagedPostLiveInternal.addSource(it) {
                pagedPostLiveInternal.postValue(it)
            }
            eventDataSource = it
        }
    }


    override fun onCleared() {
        eventDataSource?.let {
            pagedPostLiveInternal.removeSource(it)
        }
        super.onCleared()
    }

    private fun buildLivePagedList(config: PagedList.Config, progressNotifier: ProgressNotifierCallback? = null): LiveData<PagedList<Post>> {

        val dataSourceFactory = object : DataSource.Factory<Int, Post>() {
            override fun create(): DataSource<Int, Post> {
                return PostsDataSource(
                    this@PostsViewModel,
                    postsUseCase,
                    progressNotifier
                )
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config).build()
    }
    
    
}