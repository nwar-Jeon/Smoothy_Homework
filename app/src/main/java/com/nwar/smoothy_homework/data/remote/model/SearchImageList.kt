package com.nwar.smoothy_homework.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchImageList(
    @SerializedName("documents")
    val imageList : List<SearchImageModel>
) {
}