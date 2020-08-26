package com.nwar.smoothy_homework.presenter.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nwar.smoothy_homework.R
import com.nwar.smoothy_homework.databinding.ItemSearchImageBinding
import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.presenter.ui.viewholder.SearchImageViewHolder
import com.nwar.smoothy_homework.presenter.viewmodel.MainFragmentViewModel

class SearchImagePagingAdapter(val context : Context, val viewModel : MainFragmentViewModel) : PagedListAdapter<SearchImage, SearchImageViewHolder>(DIFF) {

    val layoutId get() = R.layout.item_search_image

    override fun onBindViewHolder(holder: SearchImageViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageViewHolder {
        val binding = DataBindingUtil.inflate<ItemSearchImageBinding>(LayoutInflater.from(context), layoutId, parent, false)
        return SearchImageViewHolder(binding, viewModel)
    }
}

private val DIFF = object  : DiffUtil.ItemCallback<SearchImage>() {
    override fun areContentsTheSame(oldItem: SearchImage, newItem: SearchImage): Boolean = oldItem.imageURL==newItem.imageURL && oldItem.title==newItem.imageURL

    override fun areItemsTheSame(oldItem: SearchImage, newItem: SearchImage): Boolean = oldItem.imageURL==newItem.imageURL
}