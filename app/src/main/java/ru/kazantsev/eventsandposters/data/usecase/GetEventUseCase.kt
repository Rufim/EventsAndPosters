package ru.kazantsev.eventsandposters.data.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import ru.kazantsev.eventsandposters.data.repository.EventsRepository
import ru.kazantsev.eventsandposters.model.Event
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetEventUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) : BaseSingleUseCase<Int, Event?>() {

    override fun run(param: Int): Flow<Event?> {
        return eventsRepository.getEventById(param)
    }
}