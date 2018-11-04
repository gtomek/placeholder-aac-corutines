package uk.co.tomek.jsonplaceholderdemoapp.ui.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uk.co.tomek.jsonplaceholderdemoapp.data.MainRepository
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Comment
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Post
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate.MainViewState

/**
 * ViewModel for the main screen.
 */
class MainViewModel(val mainRepository: MainRepository) : ViewModel() {

    private val mainLiveData: MediatorLiveData<MainViewState> = MediatorLiveData()

    init {
        // set initial state
        mainLiveData.value = MainViewState.Loading

        mainLiveData.addSource(mainRepository.postsLiveData) {
            mainLiveData.value = combineLatestData(mainRepository.postsLiveData, mainRepository.commentsLiveData)
        }
        mainLiveData.addSource(mainRepository.commentsLiveData) {
            mainLiveData.value = combineLatestData(mainRepository.postsLiveData, mainRepository.commentsLiveData)
        }
    }

    fun getLiveData() = mainLiveData

    fun fetchData() {
        mainRepository.fetchAll()
    }

    private fun combineLatestData(fetchPosts: MutableLiveData<List<Post>>, fetchComments: MutableLiveData<List<Comment>>): MainViewState {
        return MainViewState.Data(fetchPosts.value)
    }
}