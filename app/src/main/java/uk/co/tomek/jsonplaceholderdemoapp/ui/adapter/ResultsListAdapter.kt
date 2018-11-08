package uk.co.tomek.jsonplaceholderdemoapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_post.view.*
import timber.log.Timber
import uk.co.tomek.jsonplaceholderdemoapp.R
import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewmodel.MainViewModel

class ResultsListAdapter(private val mainViewModel: MainViewModel) :
    RecyclerView.Adapter<ResultsListAdapter.ListItemViewHolder>() {

    var posts = listOf<PostItemModel>()
        set(value) {
            field = value
            Timber.v("Set new posts :$value")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ListItemViewHolder(view, mainViewModel)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    class ListItemViewHolder(
        itemView: View,
        private val mainViewModel: MainViewModel
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostItemModel) {
            itemView.text_view_post_title.text = post.title.capitalize()
            itemView.text_view_post_body.text = post.body.capitalize()
            itemView.text_view_user_name.text = post.user.capitalize()
            itemView.text_view_counter.text = post.commentsCount.toString()
            itemView.setOnClickListener { mainViewModel.itemClicked(post) }
        }
    }
}