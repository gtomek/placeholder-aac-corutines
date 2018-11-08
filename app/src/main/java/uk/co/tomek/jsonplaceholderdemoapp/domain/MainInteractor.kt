package uk.co.tomek.jsonplaceholderdemoapp.domain

import timber.log.Timber
import uk.co.tomek.jsonplaceholderdemoapp.data.Repostitory
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Comment
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Post
import uk.co.tomek.jsonplaceholderdemoapp.data.model.User
import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel

/**
 * Iterator between the repository and UI layer.
 */
class MainInteractor(private val repository: Repostitory) {

    suspend fun fetchAll(): List<PostItemModel> {
        try {
            val posts = repository.fetchPosts()
            val comments = repository.fetchComments()
            val users = repository.fetchUsers()

            return mapToPostItems(posts, users, comments)
        } catch (exception : Exception) {
            Timber.e(exception)
            // TODO: Return an error state
        }
        return emptyList()
    }

    private fun mapToPostItems(
        posts: List<Post>,
        users: List<User>,
        comments: List<Comment>
    ): List<PostItemModel> {
        Timber.v("mapToPostItems called")
        return posts
            .map { post ->
                PostItemModel(
                    post.title,
                    post.body,
                    users
                        .filter { it.id == post.userId }
                        .map { user ->
                            user.name
                        }.firstOrNull() ?: "",
                    comments
                        .filter { it.postId == post.id }
                        .toList()
                        .count()
                )
            }
    }
}