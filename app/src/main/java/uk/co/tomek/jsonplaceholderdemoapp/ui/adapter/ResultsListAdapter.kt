package uk.co.tomek.jsonplaceholderdemoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*
import timber.log.Timber
import uk.co.tomek.jsonplaceholderdemoapp.R
import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel

class ResultsListAdapter(private val clickHandler: (PostItemModel) -> Unit) :
    RecyclerView.Adapter<ResultsListAdapter.ListItemViewHolder>() {

    var posts = listOf<PostItemModel>()
        set(value) {
            field = value
            Timber.v("Set new posts :$value")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ListItemViewHolder(view, clickHandler)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class ListItemViewHolder(
        itemView: View,
        private val clickHandler: (PostItemModel) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostItemModel) {
            itemView.text_view_list_post_title.text = post.title.capitalize()
            itemView.text_view_list_user_name.text = post.user.capitalize()
            itemView.setOnClickListener { clickHandler(post) }
        }
    }
}