package uk.co.tomek.jsonplaceholderdemoapp.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import timber.log.Timber
import uk.co.tomek.jsonplaceholderdemoapp.data.Repostitory
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Comment
import uk.co.tomek.jsonplaceholderdemoapp.data.model.Post
import uk.co.tomek.jsonplaceholderdemoapp.data.model.User
import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel
import uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate.MainViewState

/**
 * Iterator between the repository and UI layer.
 */
class MainInteractor(private val repository: Repostitory) : Interactor<MainViewState> {

    override suspend fun fetchData(): MainViewState {
        return try {
            // launch 3 network calls in parallel
            val posts = CoroutineScope(Dispatchers.IO).async {(repository.fetchPosts())}
            val comments = CoroutineScope(Dispatchers.IO).async {repository.fetchComments()}
            val users = CoroutineScope(Dispatchers.IO).async {repository.fetchUsers()}
            MainViewState.Data(mapToPostItems(posts.await(), users.await(), comments.await()))
        } catch (exception : Exception) {
            MainViewState.Error(exception, "Fetching main data has failed")
        }
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