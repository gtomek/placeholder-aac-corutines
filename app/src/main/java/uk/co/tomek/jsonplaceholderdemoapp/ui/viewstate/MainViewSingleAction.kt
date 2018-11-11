package uk.co.tomek.jsonplaceholderdemoapp.ui.viewstate

import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel

sealed class MainViewSingleAction {
    data class ItemClicked(val postItemModel: PostItemModel) : MainViewSingleAction()
}