package ru.kazantsev.eventsandposters.data.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import ru.kazantsev.eventsandposters.data.repository.EventsRepository
import ru.kazantsev.eventsandposters.model.Event
import ru.kazantsev.eventsandposters.model.Post
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetEventsUseCase  @Inject constructor(
    private val eventsRepository: EventsRepository
) : BaseSingleUseCase<Unit, List<Event>>() {

    override fun run(param: Unit): Flow<List<Event>> {
        return eventsRepository.getAllEvents()
    }
}