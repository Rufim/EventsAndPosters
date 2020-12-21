package ru.kazantsev.eventsandposters.ui.navigation

import androidx.annotation.IdRes

class NavigationArguments(
    @IdRes val destination : Int,
    val arguments : Any? = null
)  {

    companion object {
        const val POP_BACK_STACK_ACTION = 0
    }
}