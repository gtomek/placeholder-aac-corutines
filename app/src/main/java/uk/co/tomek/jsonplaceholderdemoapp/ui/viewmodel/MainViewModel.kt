package uk.co.tomek.jsonplaceholderdemoapp.ui.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uk.co.tomek.jsonplaceholderdemoapp.domain.Interactor
import uk.co.tomek.jsonplaceholderdemoapp.domain.MainInteractor
import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate.MainViewState

/**
 * ViewModel for the main screen.
 */
class MainViewModel(private val mainInteractor: Interactor<List<PostItemModel>>) : ViewModel() {

    private val mainLiveData: MediatorLiveData<MainViewState> = MediatorLiveData()

    private lateinit var job: Job

    init {
        // set initial state
        mainLiveData.value = MainViewState.Loading
    }

    fun getLiveData() = mainLiveData

    fun fetchData() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val postsItems = mainInteractor.fetchData()
            mainLiveData.postValue(MainViewState.DataState(postsItems))
        }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}