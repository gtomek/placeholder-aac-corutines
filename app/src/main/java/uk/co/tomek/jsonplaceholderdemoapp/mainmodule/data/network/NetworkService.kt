package uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data.model.Comment
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data.model.Post
import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data.model.User

/**
 * Retrofit network service definition.
 */
interface NetworkService {

    @GET("users")
    fun getUsers(): Deferred<List<User>>

    @GET("posts")
    fun getPosts(): Deferred<List<Post>>

    @GET("comments")
    fun getComments(): Deferred<List<Comment>>

}