package uk.co.tomek.jsonplaceholderdemoapp.data

import androidx.lifecycle.MutableLiveData
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

    val postsLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    val commentsLiveData: MutableLiveData<List<Comment>> = MutableLiveData()
    val usersLiveData: MutableLiveData<List<User>> = MutableLiveData()

    suspend fun fetchAll() {
        fetchPosts()
        fetchComments()
        fetchUsers()
    }

    private suspend fun fetchPosts() {
        val postsResult = networkService.getPosts().await()
        postsLiveData.postValue(postsResult)
    }

    private suspend fun fetchComments() {
        val commentsResult = networkService.getComments().await()
        commentsLiveData.postValue(commentsResult)
    }

    private suspend fun fetchUsers() {
        val usersResult = networkService.getUsers().await()
        usersLiveData.postValue(usersResult)
    }
}