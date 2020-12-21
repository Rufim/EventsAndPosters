package ru.kazantsev.eventsandposters.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.ui.fragment.EventsFragment
import ru.kazantsev.eventsandposters.ui.fragment.PostsFragment

@ExperimentalCoroutinesApi
class MainPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> PostsFragment.newInstance()
            1 -> EventsFragment.newInstance()
            else -> Fragment()
        }
    }

}