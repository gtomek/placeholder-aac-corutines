package uk.co.tomek.jsonplaceholderdemoapp.data.model

data class UsersResponse(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)