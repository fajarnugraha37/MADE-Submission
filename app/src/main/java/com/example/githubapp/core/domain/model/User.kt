package com.example.githubapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User(
    var id: Int,
    var login: String,
    var avatarUrl: String,
    var type: String,
    var name: String?,
    var company: String?,
    var email: String?,
    var bio: String?,
    var publicRepos: Int?,
    var publicGists: Int?,
    var followers: Int?,
    var following: Int?,
    var createdAt: Date?,
    var favorite: Boolean = false,
) : Parcelable {
}