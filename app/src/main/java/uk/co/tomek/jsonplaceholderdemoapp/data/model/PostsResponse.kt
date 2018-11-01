package uk.co.tomek.jsonplaceholderdemoapp.data.model

data class PostsResponse(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)