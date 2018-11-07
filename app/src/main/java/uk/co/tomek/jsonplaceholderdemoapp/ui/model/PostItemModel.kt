package uk.co.tomek.jsonplaceholderdemoapp.ui.model

/**
 * Model of the data presented in the view.
 */
data class PostItemModel(
    val title: String,
    val body: String,
    val user: String,
    val commentsCount: Int
)