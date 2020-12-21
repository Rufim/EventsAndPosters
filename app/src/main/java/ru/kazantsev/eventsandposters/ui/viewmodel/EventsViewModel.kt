package ru.kazantsev.eventsandposters.ui.viewmodel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.data.usecase.GetEventsUseCase
import ru.kazantsev.eventsandposters.model.Event
import ru.kazantsev.eventsandposters.ui.datasource.EventsDataSource
import ru.kazantsev.eventsandposters.ui.datasource.ProgressNotifierCallback
import javax.inject.Inject

@ExperimentalCoroutinesApi
class EventsViewModel @Inject constructor(private val eventsUseCase: GetEventsUseCase) : NavigationViewModel() {

    private val pagedEventLiveInternal: MediatorLiveData<PagedList<Event>> = MediatorLiveData()
    val pagedEventLive = pagedEventLiveInternal as LiveData<PagedList<Event>>
    private var eventDataSource: LiveData<PagedList<Event>>? = null


    fun load(progressNotifier: ProgressNotifierCallback? = null) {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        buildLivePagedList(config, progressNotifier).let {
            pagedEventLiveInternal.addSource(it) {
                pagedEventLiveInternal.postValue(it)
            }
            eventDataSource = it
        }
    }


    override fun onCleared() {
        eventDataSource?.let {
            pagedEventLiveInternal.removeSource(it)
        }
        super.onCleared()
    }

    private fun buildLivePagedList(config: PagedList.Config, progressNotifier: ProgressNotifierCallback? = null): LiveData<PagedList<Event>> {

        val dataSourceFactory = object : DataSource.Factory<Int, Event>() {
            override fun create(): DataSource<Int,Event> {
                return EventsDataSource(
                    this@EventsViewModel,
                    eventsUseCase,
                    progressNotifier
                )
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config).build()
    }

}