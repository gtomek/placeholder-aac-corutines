package uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate

import uk.co.tomek.jsonplaceholderdemoapp.data.model.Post

/**
 * View state rendered in Main Activity.
 */
sealed class MainViewState {
    object Loading : MainViewState()
    data class Error(val throwable: Throwable? = null, val message: String? = null) : MainViewState()
    data class Data(val postsResponse: List<Post>?) : MainViewState()
}