package ru.kazantsev.eventsandposters.ui.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.model.Post
import ru.kazantsev.eventsandposters.ui.MainActivity
import ru.kazantsev.eventsandposters.ui.adapter.PostsAdapter
import ru.kazantsev.eventsandposters.ui.viewModelOf
import ru.kazantsev.eventsandposters.ui.viewmodel.EventsViewModel
import ru.kazantsev.eventsandposters.ui.viewmodel.PostsViewModel

@ExperimentalCoroutinesApi
class PostsFragment : ListFragment<PostsViewModel>() {

    companion object {
        fun newInstance() = PostsFragment()
    }

    override val adapter = PostsAdapter(onItemClick())

    fun onItemClick() = object : PostsAdapter.OnItemClickListener {
        override fun onItemClicked(post: Post) {
            post.links_to?.let {
                requireActivity().startActivity(MainActivity.getDeepLinkIntent(it))
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (adapter.itemCount == 0) refresh()
        observeDataSource()
    }

    private fun refresh() {
        viewModel.load(this)
    }

    private fun observeDataSource() {
        viewModel.pagedPostLive.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun initViewModel() = viewModelOf<PostsViewModel>(viewModelProvider)
}