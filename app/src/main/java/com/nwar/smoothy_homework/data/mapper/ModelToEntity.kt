package com.nwar.smoothy_homework.data.mapper

import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.data.remote.model.SearchImageModel

fun SearchImageModel.toSearchImage() = SearchImage(this.title, this.imageURL)