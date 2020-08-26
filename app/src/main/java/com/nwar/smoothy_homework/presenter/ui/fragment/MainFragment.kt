package com.nwar.smoothy_homework.presenter.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nwar.smoothy_homework.R
import com.nwar.smoothy_homework.databinding.FragmentMainBinding
import com.nwar.smoothy_homework.presenter.ui.adapter.SearchImageAdapter
import com.nwar.smoothy_homework.presenter.viewmodel.MainFragmentViewModel

class MainFragment() : BaseFragment<FragmentMainBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_main

    override val viewModel: MainFragmentViewModel by lazy { ViewModelProvider(this)[MainFragmentViewModel::class.java] }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = SearchImageAdapter(requireContext(), viewModel)
        binding.mainRecyclerView.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(requireContext(),1)
        }
        viewModel.searchListLiveData.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
    }
}