package uk.co.tomek.jsonplaceholderdemoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import uk.co.tomek.jsonplaceholderdemoapp.R
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewmodel.MainViewModel
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate.MainViewState

class MainActivity : AppCompatActivity() {

    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getLiveData().observe(this, Observer { viewState ->
            if (viewState != null) renderDataState(viewState)
        })
        mainViewModel.fetchData()
    }

    private fun renderDataState(state: MainViewState) {
        Timber.v("Render state $state")
        when (state) {
            is MainViewState.Loading -> {
            }
            is MainViewState.Data -> {
            }
            is MainViewState.Error -> {
            }
        }
    }
}
