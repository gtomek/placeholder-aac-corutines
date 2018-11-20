package uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.domain.Interactor
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.model.PostItemModel
import uk.co.tomek.jsonplaceholderdemoapp.core.other.SingleLiveEvent
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.viewstate.MainViewSingleAction
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.viewstate.MainViewState

/**
 * ViewModel for the main screen.
 */
class MainViewModel(private val mainInteractor: Interactor<MainViewState>) : ViewModel() {

    private val mainLiveData = MutableLiveData<MainViewState>()

    // using it to open details screen
    // as recommended e.g. in
    // https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
    private val singleEvent : SingleLiveEvent<MainViewSingleAction> =
        SingleLiveEvent()

    private lateinit var job: Job

    init {
        // set initial state
        mainLiveData.value = MainViewState.Loading
        fetchData()
    }

    fun getLiveData() = mainLiveData

    fun getViewActions() = singleEvent

    private fun fetchData() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val mainViewState = mainInteractor.fetchData()
            mainLiveData.postValue(mainViewState)
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

    fun retryButtonClicked() {
        mainLiveData.value = MainViewState.Loading
        fetchData()
    }

    fun itemClicked(model: PostItemModel) {
        singleEvent.value = MainViewSingleAction.ItemClicked(model)
    }
}