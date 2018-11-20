package uk.co.tomek.jsonplaceholderdemoapp.mainmodule.data.model

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)