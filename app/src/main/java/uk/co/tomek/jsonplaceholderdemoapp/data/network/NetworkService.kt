package uk.co.tomek.jsonplaceholderdemoapp.data.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import uk.co.tomek.jsonplaceholderdemoapp.data.model.CommentsResponse
import uk.co.tomek.jsonplaceholderdemoapp.data.model.PostsResponse
import uk.co.tomek.jsonplaceholderdemoapp.data.model.UsersResponse

/**
 * Retrofit network service definition.
 */
interface NetworkService {

    @GET("users")
    fun getUsers(): Deferred<Response<UsersResponse>>

    @GET("posts")
    fun getPosts(): Deferred<Response<PostsResponse>>

    @GET("comments")
    fun getComments(): Deferred<Response<CommentsResponse>>

}