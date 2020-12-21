package ru.kazantsev.eventsandposters.ui.fragment

import android.os.Bundle
import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.R
import ru.kazantsev.eventsandposters.model.Event
import ru.kazantsev.eventsandposters.ui.adapter.EventsAdapter
import ru.kazantsev.eventsandposters.ui.navigation.NavigationArguments
import ru.kazantsev.eventsandposters.ui.viewModelOf
import ru.kazantsev.eventsandposters.ui.viewmodel.EventsViewModel

@ExperimentalCoroutinesApi
class EventsFragment :  ListFragment<EventsViewModel>() {

    companion object {
        fun newInstance() = EventsFragment()
    }

    override val adapter = EventsAdapter(onItemClick())

    fun onItemClick() = object : EventsAdapter.OnItemClickListener {
        override fun onItemClicked(event: Event) {
            viewModel.navigateTo(NavigationArguments(R.id.nav_action_event_detail,
                event.id
            ))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (adapter.itemCount == 0 ) refresh()
        observeDataSource()
    }

    private fun refresh() {
        viewModel.load(this)
    }

    private fun observeDataSource() {
        viewModel.pagedEventLive.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun initViewModel() = viewModelOf<EventsViewModel>(viewModelProvider)



}