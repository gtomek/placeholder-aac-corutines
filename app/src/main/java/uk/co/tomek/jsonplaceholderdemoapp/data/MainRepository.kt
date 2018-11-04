package uk.co.tomek.jsonplaceholderdemoapp.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Comment
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Post
import uk.co.tomek.jsonplaceholderdemoapp.data.model.User
import uk.co.tomek.jsonplaceholderdemoapp.data.network.NetworkService

/**
 * Repository
 * following suggestions from
 * https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7
 */
class MainRepository(val networkService: NetworkService) {

    // TODO: make them private
    val postsLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    val commentsLiveData: MutableLiveData<List<Comment>> = MutableLiveData()
    private val usersLiveData: MutableLiveData<List<User>> = MutableLiveData()

    fun fetchAll() {
        fetchPosts()
        fetchComments()
    }

    private fun fetchPosts() {
        GlobalScope.launch {
            val postsResult = networkService.getPosts().await()
            withContext(Dispatchers.Main) {
                postsLiveData.postValue(postsResult)
            }
        }
    }

    private fun fetchComments() {
        GlobalScope.launch {
            val commentsResult = networkService.getComments().await()
            withContext(Dispatchers.Main) {
                commentsLiveData.postValue(commentsResult)
            }
        }
    }

    private fun fetchUsers() {
        GlobalScope.launch {
            val usersResult = networkService.getUsers().await()
            withContext(Dispatchers.Main) {
                usersLiveData.postValue(usersResult)
            }
        }
    }
}