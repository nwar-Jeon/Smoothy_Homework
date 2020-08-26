package com.nwar.smoothy_homework.presenter.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.nwar.smoothy_homework.R
import com.nwar.smoothy_homework.databinding.ItemSearchImageBinding
import com.nwar.smoothy_homework.domain.entity.SearchImage
import com.nwar.smoothy_homework.presenter.ui.viewholder.SearchImageViewHolder
import com.nwar.smoothy_homework.presenter.viewmodel.MainFragmentViewModel

class SearchImageAdapter(val context : Context, val viewModel : MainFragmentViewModel) : RecyclerView.Adapter<SearchImageViewHolder>() {

    private var itemList : List<Any>? = null
    private val layoutId get() = R.layout.item_search_image

    override fun getItemCount(): Int = itemList?.size ?: 0

    override fun onBindViewHolder(holder: SearchImageViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchImageViewHolder {
        val binding = DataBindingUtil.inflate<ItemSearchImageBinding>(LayoutInflater.from(context), layoutId, parent, false)
        return SearchImageViewHolder(binding, viewModel)
    }

    fun submitList(items : List<Any>) {
        itemList = items
        notifyDataSetChanged()
    }
}