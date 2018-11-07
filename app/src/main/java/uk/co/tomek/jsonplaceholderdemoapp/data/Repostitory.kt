package uk.co.tomek.jsonplaceholderdemoapp.data

import uk.co.tomek.jsonplaceholderdemoapp.data.model.Comment
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Post
import uk.co.tomek.jsonplaceholderdemoapp.data.model.User

/**
 * Repository abstraction.
 */
interface Repostitory {
    suspend fun fetchPosts() : List<Post>
    suspend fun fetchComments(): List<Comment>
    suspend fun fetchUsers(): List<User>
}