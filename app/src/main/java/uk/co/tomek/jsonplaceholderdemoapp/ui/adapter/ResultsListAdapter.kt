package uk.co.tomek.jsonplaceholderdemoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*
import timber.log.Timber
import uk.co.tomek.jsonplaceholderdemoapp.R
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Post

class ResultsListAdapter : RecyclerView.Adapter<ResultsListAdapter.ListItemViewHolder>() {

    var posts = listOf<Post>()
        set(value) {
            field = value
            Timber.v("Set new posts :$value")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.text_view_post_title.text = post.title
            itemView.text_view_post_body.text = post.body
            // TODO: Finish name and count
            itemView.text_view_user_name.text = post.userId.toString()
        }
    }
}