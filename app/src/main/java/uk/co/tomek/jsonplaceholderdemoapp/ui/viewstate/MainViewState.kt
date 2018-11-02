package uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate

/**
 * View state rendered in Main Activity.
 */
sealed class MainViewState {
    object Loading : MainViewState()
    data class Error(private val throwable: Throwable? = null, private val message: String? = null) : MainViewState()
    data class Data(private val messageToDo: String? = null) : MainViewState()
}