package ru.kazantsev.eventsandposters.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kazantsev.eventsandposters.databinding.PostCardBinding
import ru.kazantsev.eventsandposters.model.Idable
import ru.kazantsev.eventsandposters.model.Post
import ru.kazantsev.eventsandposters.ui.convertDate

class PostsAdapter(private val onItemClickListener: OnItemClickListener) : PagedListAdapter<Post, PostsAdapter.PostsViewHolder>(getDiffIdableCallback())  {

    inner class PostsViewHolder(val binding: PostCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post, onItemClickListener: OnItemClickListener? = null) {
            with(binding) {
                postComment.text = post.comment
                postDate.text = convertDate(post.published_at)
                postButton.visibility = View.GONE
                if (post.links_to != null) {
                    postButton.visibility = View.VISIBLE
                    onItemClickListener?.let { listener ->
                        postButton.setOnClickListener {
                            listener.onItemClicked(post)
                        }
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(post: Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostCardBinding.inflate(
            inflater,
            parent,
            false
        )
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClickListener)
        }
    }


}