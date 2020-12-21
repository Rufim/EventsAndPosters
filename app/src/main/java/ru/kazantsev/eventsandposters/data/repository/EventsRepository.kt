package ru.kazantsev.eventsandposters.data.repository

import androidx.annotation.MainThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import ru.kazantsev.eventsandposters.data.api.ApiService
import ru.kazantsev.eventsandposters.data.database.dao.EventsDao
import ru.kazantsev.eventsandposters.model.Event
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class EventsRepository @Inject constructor(
    private val eventsDao: EventsDao,
    private val apiService: ApiService
) {

    fun getAllEvents() = flow {
        try {
            val apiResponse = apiService.getEvents()
            val events = apiResponse.body()
            if (apiResponse.isSuccessful && events != null) {
                // Save posts into the persistence storage
                eventsDao.deleteAllEvents()
                eventsDao.insertEvents(events)
                emit(events)
                return@flow
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
        emitAll(eventsDao.getAllEvents())
    }

    fun getEventById(eventId: Int) = flow {
        eventsDao.getEventById(eventId).collect {
            if (it != null) emit(it) else {
                getAllEvents().collect {
                    it.find { it.id == eventId }?.let {
                        emit(it)
                    } ?: run {
                        emit(null)
                    }
                }
            }
        }
    }
}