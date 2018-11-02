package uk.co.tomek.jsonplaceholderdemoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate.MainViewState

/**
 * ViewModel for the main screen.
 */
class MainViewModel : ViewModel() {

    private val mainLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    fun getLiveData() = mainLiveData

    fun fetchData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}