package ru.kazantsev.eventsandposters.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.ar2code.mutableliveevent.EventArgs
import ru.ar2code.mutableliveevent.MutableLiveEvent
import ru.kazantsev.eventsandposters.data.usecase.BaseSingleUseCase
import ru.kazantsev.eventsandposters.data.usecase.GetEventUseCase
import ru.kazantsev.eventsandposters.model.Event
import javax.inject.Inject

@ExperimentalCoroutinesApi
class EventDetailsViewModel @Inject constructor(private val getEventUseCase: GetEventUseCase) : NavigationViewModel() {

    private val internalEventLiveData = MutableLiveEvent< EventArgs<Event>>()
    val eventLiveData = internalEventLiveData as LiveData<EventArgs<Event>>

    fun getEvent(eventId: Int) {
        viewModelScope.launch {
            getEventUseCase(eventId).collect {
                if (it is BaseSingleUseCase.State.Success) {
                    internalEventLiveData.postValue(EventArgs(it.data))
                }
            }
        }
    }


}