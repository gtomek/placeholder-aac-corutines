package uk.co.tomek.jsonplaceholderdemoapp.data.model

data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)