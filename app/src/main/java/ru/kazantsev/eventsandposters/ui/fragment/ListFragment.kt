package ru.kazantsev.eventsandposters.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kazantsev.eventsandposters.R
import ru.kazantsev.eventsandposters.databinding.ListFragmentBinding
import ru.kazantsev.eventsandposters.model.Event
import ru.kazantsev.eventsandposters.ui.adapter.EventsAdapter
import ru.kazantsev.eventsandposters.ui.datasource.ProgressNotifierCallback
import ru.kazantsev.eventsandposters.ui.hide
import ru.kazantsev.eventsandposters.ui.show
import ru.kazantsev.eventsandposters.ui.viewmodel.NavigationViewModel


abstract class ListFragment<VM : NavigationViewModel> : BaseFragment<VM, ListFragmentBinding>(R.layout.list_fragment), ProgressNotifierCallback  {


    abstract val adapter: PagedListAdapter<*,*>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState).also {
            binding.listView.adapter = adapter
        }
    }

    override fun onStartLoad() {
        binding.apply {
            listLoadingProgress.show()
            listLoadingError.hide()
        }
    }

    override fun onFinishLoad() {
        binding.apply {
            listLoadingProgress.hide()
            listLoadingError.hide()
        }
    }

    override fun onError() {
        binding.apply {
            listLoadingProgress.hide()
            listLoadingError.show()
        }
    }
}