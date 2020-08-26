package com.nwar.smoothy_homework.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchImageModel(
    @SerializedName("thumbnail_url")
    val imageURL : String,
    @SerializedName("display_sitename")
    val title : String
) {
}