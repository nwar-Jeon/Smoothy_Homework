package com.nwar.smoothy_homework.domain.entity

data class SearchImage(
    val title : String,
    val imageURL : String,
    var isFavorite : Boolean = false
) {
    override fun equals(other: Any?): Boolean = if(other !is SearchImage) false else imageURL == other.imageURL

    override fun hashCode(): Int = imageURL.hashCode()

    fun toggleFavorite() { isFavorite = !isFavorite }
}