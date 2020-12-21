package ru.kazantsev.eventsandposters.ui.navigation

import android.app.Activity
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ru.kazantsev.eventsandposters.MainGraphDirections
import ru.kazantsev.eventsandposters.R
import ru.kazantsev.eventsandposters.ui.fragment.BaseFragment

class AppNavigator {

    fun navigateTo(fragment: BaseFragment<*,*>, navigationArguments: NavigationArguments) {
        when (navigationArguments.destination) {
            NavigationArguments.POP_BACK_STACK_ACTION -> navigateBack(fragment)
            R.id.nav_action_tabs_fragment -> navigateToMainTabs(fragment, navigationArguments)
            R.id.nav_action_event_detail -> navigateToEventDetails(fragment, navigationArguments)
        }
    }

    private fun navigateBack(fragment: BaseFragment<*,*>) {
        fragment.findNavController().popBackStack()
    }

    fun navigateToMainTabs(fragment: BaseFragment<*,*>, navigationArguments: NavigationArguments) {
        val action = MainGraphDirections.navActionTabsFragment(
            navigationArguments.arguments as Int
        )
        getTabsNavigator(fragment).navigate(action)
    }

    fun navigateToEventDetails(fragment: BaseFragment<*,*>, navigationArguments: NavigationArguments) {
        val action = MainGraphDirections.navActionEventDetail(
            navigationArguments.arguments as Int
        )
        getTabsNavigator(fragment).navigate(action)
    }

    /**
     * Navigate to actions from [res/navigation/main_graph.xml]
     */
    private fun getTabsNavigator(fragment: BaseFragment<*,*>): NavController {
        return try {
            fragment.requireActivity().findNavController(R.id.tabsNavHostFragment)
        } catch (e: Throwable) {
            fragment.findNavController()
        }
    }


}