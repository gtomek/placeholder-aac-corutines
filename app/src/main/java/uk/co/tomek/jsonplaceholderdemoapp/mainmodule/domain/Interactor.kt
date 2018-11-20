package uk.co.tomek.jsonplaceholderdemoapp.mainmodule.domain

interface Interactor<T> {

    suspend fun fetchData(): T
}