package uk.co.tomek.jsonplaceholderdemoapp.ui

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view_results_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ResultsListAdapter()
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
            is MainViewState.Data -> {
                recycler_view_results_list.visibility = View.VISIBLE
                image_view_progress.visibility = View.GONE
            }
            is MainViewState.Error -> {
                recycler_view_results_list.visibility = View.GONE
                image_view_progress.visibility = View.GONE
                // TODO: Display some error
            }
        }
    }
}
