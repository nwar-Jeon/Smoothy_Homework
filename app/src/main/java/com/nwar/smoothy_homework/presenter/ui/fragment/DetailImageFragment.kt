package com.nwar.smoothy_homework.presenter.ui.fragment

import com.nwar.smoothy_homework.R
import com.nwar.smoothy_homework.databinding.FragmentDetailImageBinding
import com.nwar.smoothy_homework.presenter.viewmodel.DetailImageViewModel

class DetailImageFragment() : BaseFragment<FragmentDetailImageBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_detail_image

    override val viewModel by lazy { DetailImageViewModel() }

    override fun setViewModel() {
        binding.vm = viewModel
    }
}