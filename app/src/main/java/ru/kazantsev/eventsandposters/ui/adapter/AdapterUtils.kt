package ru.kazantsev.eventsandposters.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.kazantsev.eventsandposters.model.Idable

fun <T : Idable> getDiffIdableCallback() : DiffUtil.ItemCallback<T>  {
    return object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(
            oldItem: T,
            newItem: T
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem:T, newItem: T): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

