package com.nwar.smoothy_homework.presenter.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.nwar.smoothy_homework.presenter.lifecycle.LifecycleOwner
import com.nwar.smoothy_homework.presenter.viewmodel.BaseViewModel
import org.jetbrains.anko.support.v4.toast

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    private val lifecycleOwner = LifecycleOwner()

    abstract val viewModel : BaseViewModel

    lateinit var binding : T

    abstract val layoutId : Int

    private val navController by lazy { findNavController() }

    abstract fun setViewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycleOwner.register(viewModel)
        lifecycleOwner.event(Lifecycle.Event.ON_CREATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setBinding(inflater, container)
        setViewModel()
        setObserveViewModel()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        lifecycleOwner.event(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleOwner.event(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifecycleOwner.event(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifecycleOwner.event(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleOwner.event(Lifecycle.Event.ON_DESTROY)
        lifecycleOwner.unRegister(viewModel)
    }

    private fun setBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
    }

    private fun setObserveViewModel() {
        viewModel.toast.observe(viewLifecycleOwner, Observer { if(!it.isNullOrEmpty()) toast(it) })
        viewModel.nextFragment.observe(viewLifecycleOwner, Observer { if(it!=null) navController.navigate(it) } )
        viewModel.backNavigate.observe(viewLifecycleOwner, Observer { if(it) navController.navigateUp() } )
    }
}