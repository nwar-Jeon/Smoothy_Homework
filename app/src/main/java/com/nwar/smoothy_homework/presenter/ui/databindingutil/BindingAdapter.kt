package com.nwar.smoothy_homework.presenter.ui.databindingutil

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageURL")
fun AppCompatImageView.loadImageFromURL(url : String?) {
    Glide.with(this).load(url).into(this)
}