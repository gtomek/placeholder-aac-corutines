package uk.co.tomek.jsonplaceholderdemoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details.*
import uk.co.tomek.jsonplaceholderdemoapp.R
import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar_detalils)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        intent?.getParcelableExtra<PostItemModel>(KEY_POST_DETAILS)?.let {
            bindPost(it)
        }
    }

    private fun bindPost(post: PostItemModel) {
        text_view_post_title.text = post.title.capitalize()
        text_view_post_body.text = post.body.capitalize()
        text_view_user_name.text = post.user.capitalize()
        text_view_counter.text = post.commentsCount.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val KEY_POST_DETAILS = "key_post_details"
    }
}
