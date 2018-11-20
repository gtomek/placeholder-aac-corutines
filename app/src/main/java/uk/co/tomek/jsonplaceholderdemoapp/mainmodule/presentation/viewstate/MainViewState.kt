package uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.viewstate

import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.model.PostItemModel

/**
 * View state rendered in Main Activity.
 */
sealed class MainViewState {
    object Loading : MainViewState()
    data class Error(val throwable: Throwable? = null, val message: String? = null) : MainViewState()
    data class Data(val postsResponse: List<PostItemModel>) : MainViewState()
}