package com.nwar.smoothy_homework.presenter.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.nwar.smoothy_homework.databinding.ItemSearchImageBinding
import com.nwar.smoothy_homework.presenter.viewmodel.MainFragmentViewModel

class SearchImageViewHolder(val binding : ItemSearchImageBinding, val viewModel : MainFragmentViewModel) : RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.vm = viewModel
        binding.position = adapterPosition
    }
}