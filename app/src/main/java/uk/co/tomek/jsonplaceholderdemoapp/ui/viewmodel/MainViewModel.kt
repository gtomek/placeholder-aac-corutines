package uk.co.tomek.jsonplaceholderdemoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.co.tomek.jsonplaceholderdemoapp.data.network.NetworkService
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate.MainViewState

/**
 * ViewModel for the main screen.
 */
class MainViewModel(val networkService: NetworkService) : ViewModel() {

    private val mainLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    fun getLiveData() = mainLiveData

    fun fetchData() {
        GlobalScope.launch {
            val postsResult = networkService.getPosts().await()
            withContext(Dispatchers.Main) {
                mainLiveData.postValue(MainViewState.Data(postsResult))
            }
        }

    }
}