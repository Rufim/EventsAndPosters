package ru.kazantsev.eventsandposters.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.R
import ru.kazantsev.eventsandposters.databinding.EvemtDetailsBinding
import ru.kazantsev.eventsandposters.ui.convertDate
import ru.kazantsev.eventsandposters.ui.viewModelOf
import ru.kazantsev.eventsandposters.ui.viewmodel.EventDetailsViewModel

@ExperimentalCoroutinesApi
class EventDetailsFragment : BaseFragment<EventDetailsViewModel, EvemtDetailsBinding>(R.layout.evemt_details) {

    private val args: EventDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeEvent()
        getEvent()
    }

    fun observeEvent() {
        viewModel.eventLiveData.observe(viewLifecycleOwner, {
            it.data?.let {
                binding.eventDetailsLocation.text = it.location
                binding.eventDetailsDate.text = convertDate(it.event_datetime)
                binding.eventDetailsDescription.text = it.description
            }
        })
    }

    fun getEvent() {
        viewModel.getEvent(args.eventId)
    }

    override fun initViewModel() = viewModelOf<EventDetailsViewModel>(viewModelProvider)
}