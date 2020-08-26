package com.nwar.smoothy_homework.presenter.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.nwar.smoothy_homework.R
import com.nwar.smoothy_homework.databinding.ItemFavoriteBinding
import com.nwar.smoothy_homework.presenter.ui.viewholder.FavoriteViewHolder
import com.nwar.smoothy_homework.presenter.viewmodel.FavoriteFragmentViewModel

class FavoriteAdapter(val context : Context, val viewModel : FavoriteFragmentViewModel) : RecyclerView.Adapter<FavoriteViewHolder>() {

    private var itemList : List<Any>? = null
    private val layoutId : Int get() = R.layout.item_favorite

    override fun getItemCount(): Int = itemList?.size ?: 0

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = DataBindingUtil.inflate<ItemFavoriteBinding>(LayoutInflater.from(context), layoutId, parent, false)
        return FavoriteViewHolder(binding, viewModel)
    }

    fun submitList(list : List<Any>) {
        itemList = list
        notifyDataSetChanged()
    }
}