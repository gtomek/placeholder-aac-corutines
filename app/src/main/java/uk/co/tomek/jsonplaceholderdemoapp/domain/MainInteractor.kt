package uk.co.tomek.jsonplaceholderdemoapp.domain

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
        val posts = repository.fetchPosts()
        val comments = repository.fetchComments()
        val users = repository.fetchUsers()

        return mapToPostItems(posts, users, comments)
    }

    private fun mapToPostItems(
        posts: List<Post>,
        users: List<User>,
        comments: List<Comment>
    ): List<PostItemModel> {
        return posts
            .map { post ->
                PostItemModel(
                    post.title,
                    post.body,
                    users
                        .filter { it.id == post.userId }
                        .map { user ->
                            user.name
                        }.first(),
                    comments
                        .filter { it.postId == post.id }
                        .toList()
                        .count()
                )
            }
    }
}