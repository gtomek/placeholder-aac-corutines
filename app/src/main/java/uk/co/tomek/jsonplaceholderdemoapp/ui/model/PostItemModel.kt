package uk.co.tomek.jsonplaceholderdemoapp.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Model of the data presented in the view.
 */
@Parcelize
data class PostItemModel(
    val title: String,
    val body: String,
    val user: String,
    val commentsCount: Int
): Parcelable