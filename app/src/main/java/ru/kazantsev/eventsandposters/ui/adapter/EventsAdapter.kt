package ru.kazantsev.eventsandposters.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kazantsev.eventsandposters.databinding.EventCardBinding
import ru.kazantsev.eventsandposters.model.Event
import ru.kazantsev.eventsandposters.model.Idable
import ru.kazantsev.eventsandposters.ui.convertDate

class EventsAdapter(private val onItemClickListener: OnItemClickListener) : PagedListAdapter<Event, EventsAdapter.EventsViewHolder>(getDiffIdableCallback())  {

    val items = arrayListOf<Event>()

    inner class EventsViewHolder(val binding: EventCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event, onItemClickListener: OnItemClickListener? = null) {
            with(binding) {
                eventLocation.text = event.location
                eventDate.text = convertDate(event.event_datetime)
                onItemClickListener?.let { listener ->
                    root.setOnClickListener {
                        listener.onItemClicked(event)
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(event: Event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventCardBinding.inflate(
            inflater,
            parent,
            false
        )
        return EventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClickListener)
        }
    }



}