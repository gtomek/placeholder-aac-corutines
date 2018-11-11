package uk.co.tomek.jsonplaceholderdemoapp.domain

import uk.co.tomek.jsonplaceholderdemoapp.ui.model.PostItemModel

interface Interactor<T> {

    suspend fun fetchData(): T
}