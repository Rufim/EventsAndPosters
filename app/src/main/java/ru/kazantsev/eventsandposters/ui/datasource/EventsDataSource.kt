package ru.kazantsev.eventsandposters.ui.datasource

import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.data.usecase.GetEventsUseCase
import ru.kazantsev.eventsandposters.model.Event
import ru.kazantsev.eventsandposters.ui.viewmodel.EventsViewModel

@ExperimentalCoroutinesApi
class EventsDataSource(
    viewModel: EventsViewModel,
    postsUseCase: GetEventsUseCase,
    progressNotifier: ProgressNotifierCallback? = null
) : SimpleDataSource<Unit, Event>(viewModel, postsUseCase, progressNotifier) {


    override fun getParamByKey(key: Int?) = Unit

}