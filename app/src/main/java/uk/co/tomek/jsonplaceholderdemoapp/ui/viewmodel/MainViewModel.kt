package uk.co.tomek.jsonplaceholderdemoapp.ui.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uk.co.tomek.jsonplaceholderdemoapp.data.MainRepository
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Comment
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Post
import uk.co.tomek.jsonplaceholderdemoapp.data.model.User
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate.MainViewState

/**
 * ViewModel for the main screen.
 */
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val mainLiveData: MediatorLiveData<MainViewState> = MediatorLiveData()

    private lateinit var job: Job

    init {
        // set initial state
        mainLiveData.value = MainViewState.Loading

        mainLiveData.addSource(mainRepository.postsLiveData) {
            mainLiveData.value = combineLatestData(
                mainRepository.postsLiveData,
                mainRepository.commentsLiveData,
                mainRepository.usersLiveData
            )
        }
        mainLiveData.addSource(mainRepository.commentsLiveData) {
            mainLiveData.value = combineLatestData(
                mainRepository.postsLiveData,
                mainRepository.commentsLiveData,
                mainRepository.usersLiveData
            )
        }
        mainLiveData.addSource(mainRepository.usersLiveData) {
            mainLiveData.value = combineLatestData(mainRepository.postsLiveData, mainRepository.commentsLiveData,
                mainRepository.usersLiveData)
        }
    }

    fun getLiveData() = mainLiveData

    fun fetchData() {
        job = CoroutineScope(Dispatchers.IO).launch {
            mainRepository.fetchAll()
        }
    }

    private fun combineLatestData(
        fetchPosts: MutableLiveData<List<Post>>,
        fetchComments: MutableLiveData<List<Comment>>,
        usersLiveData: MutableLiveData<List<User>>
    ): MainViewState {
        return MainViewState.Data(fetchPosts.value)
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}