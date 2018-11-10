package uk.co.tomek.jsonplaceholderdemoapp.ui

import android.content.Intent
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import uk.co.tomek.jsonplaceholderdemoapp.R
import uk.co.tomek.jsonplaceholderdemoapp.ui.adapter.ResultsListAdapter
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewmodel.MainViewModel
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate.MainViewState

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var resultsListAdapter : ResultsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        resultsListAdapter = ResultsListAdapter { model ->
            val launchIntent = Intent(this, DetailsActivity::class.java)
            launchIntent.putExtra(DetailsActivity.KEY_POST_DETAILS, model)
            startActivity(launchIntent)
        }
        recycler_view_results_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = resultsListAdapter
        }

        mainViewModel.getLiveData().observe(this, Observer { viewState ->
            if (viewState != null) renderDataState(viewState)
        })
        mainViewModel.fetchData()
    }

    private fun renderDataState(state: MainViewState) {
        Timber.v("Render state $state")
        when (state) {
            is MainViewState.Loading -> {
                image_view_progress.apply {
                    visibility = View.VISIBLE
                    (drawable as? Animatable)?.start()
                }
                recycler_view_results_list.visibility = View.GONE
            }
            is MainViewState.DataState -> {
                recycler_view_results_list.visibility = View.VISIBLE
                image_view_progress.visibility = View.GONE
                state.postsResponse.let {
                    resultsListAdapter.posts = it
                }
            }
            is MainViewState.Error -> {
                recycler_view_results_list.visibility = View.GONE
                image_view_progress.visibility = View.GONE
                // TODO: Display some error
            }
        }
    }
}
