package uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.viewstate

import uk.co.tomek.jsonplaceholderdemoapp.mainmodule.presentation.model.PostItemModel

sealed class MainViewSingleAction {
    data class ItemClicked(val postItemModel: PostItemModel) : MainViewSingleAction()
}