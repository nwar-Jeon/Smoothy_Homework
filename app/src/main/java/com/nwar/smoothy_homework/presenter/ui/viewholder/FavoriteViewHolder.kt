package com.nwar.smoothy_homework.presenter.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.nwar.smoothy_homework.databinding.ItemFavoriteBinding
import com.nwar.smoothy_homework.presenter.viewmodel.FavoriteFragmentViewModel

class FavoriteViewHolder(val binding : ItemFavoriteBinding, val viewModel: FavoriteFragmentViewModel) : RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.vm = viewModel
        binding.position = adapterPosition
    }
}