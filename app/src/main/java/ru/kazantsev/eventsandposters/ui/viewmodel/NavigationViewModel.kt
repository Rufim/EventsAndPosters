package ru.kazantsev.eventsandposters.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.ar2code.mutableliveevent.EventArgs
import ru.kazantsev.eventsandposters.ui.navigation.NavigationArguments
import ru.kazantsev.eventsandposters.ui.navigation.NavigationArguments.Companion.POP_BACK_STACK_ACTION
import ru.kazantsev.eventsandposters.ui.utils.SingleLiveEvent

abstract class NavigationViewModel : ViewModel() {

    private val navigateEventInternal = SingleLiveEvent<EventArgs<NavigationArguments>>()
    val navigateEvent = navigateEventInternal as LiveData<EventArgs<NavigationArguments>>

    open fun navigateTo(arguments: NavigationArguments) {
        navigateEventInternal.postValue(EventArgs(arguments))
    }

    fun navigateBack() {
        navigateTo(NavigationArguments(POP_BACK_STACK_ACTION))
    }

}