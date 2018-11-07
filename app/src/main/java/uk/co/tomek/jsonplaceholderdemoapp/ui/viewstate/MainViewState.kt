package uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate

import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel

/**
 * View state rendered in Main Activity.
 */
sealed class MainViewState {
    object Loading : MainViewState()
    data class Error(val throwable: Throwable? = null, val message: String? = null) : MainViewState()
    data class DataState(val postsResponse: List<PostItemModel>) : MainViewState()
}