package uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data

import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data.network.NetworkService

/**
 * Repository
 * following suggestions from
 * https://medium.com/androiddevelopers/livedata-beyond-the-viewmodel-reactive-patterns-using-transformations-and-mediatorlivedata-fda520ba00b7
 */
class MainRepository(private val networkService: NetworkService) :
    Repostitory {

    override suspend fun fetchPosts() = networkService.getPosts().await()

    override suspend fun fetchComments() = networkService.getComments().await()

    override suspend fun fetchUsers() = networkService.getUsers().await()
}