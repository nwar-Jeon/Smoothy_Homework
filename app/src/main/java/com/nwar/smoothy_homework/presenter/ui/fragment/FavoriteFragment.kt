package com.nwar.smoothy_homework.presenter.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nwar.smoothy_homework.R
import com.nwar.smoothy_homework.databinding.FragmentFavoritesBinding
import com.nwar.smoothy_homework.presenter.ui.adapter.FavoriteAdapter
import com.nwar.smoothy_homework.presenter.ui.adapter.SearchImageAdapter
import com.nwar.smoothy_homework.presenter.viewmodel.FavoriteFragmentViewModel

class FavoriteFragment() : BaseFragment<FragmentFavoritesBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_favorites

    override val viewModel: FavoriteFragmentViewModel by lazy { ViewModelProvider(this)[FavoriteFragmentViewModel::class.java] }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = FavoriteAdapter(requireContext(), viewModel)
        binding.favoriteRecyclerView.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(requireContext(), 1)
        }
        viewModel.favoritesLiveData.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
    }
}