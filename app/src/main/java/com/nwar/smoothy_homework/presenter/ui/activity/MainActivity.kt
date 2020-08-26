package com.nwar.smoothy_homework.presenter.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.nwar.smoothy_homework.R
import com.nwar.smoothy_homework.app.context.ApplicationContextObject
import com.nwar.smoothy_homework.databinding.ActivityMainBinding
import com.nwar.smoothy_homework.presenter.lifecycle.LifecycleOwner
import com.nwar.smoothy_homework.presenter.viewmodel.ActivityViewModel

class MainActivity : AppCompatActivity() {

    private val lifecycleOwner = LifecycleOwner()

    val viewModel by lazy { ViewModelProvider(this)[ActivityViewModel::class.java] }
    private val binding by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main) }

    private val navController by lazy { findNavController(R.id.main_view_pager) }
    private val mainFragmentsId = mutableSetOf(R.id.mainFragment, R.id.favoriteFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ApplicationContextObject.setApplication(application)

        setViewModel()

        lifecycleOwner.register(viewModel)
        lifecycleOwner.event(Lifecycle.Event.ON_CREATE)

        setUpBottomNavigation()
    }

    override fun onStart() {
        super.onStart()
        lifecycleOwner.event(Lifecycle.Event.ON_STOP)
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

    private fun setViewModel() {
        binding.vm = viewModel
    }

    private fun setUpBottomNavigation() {
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        navController.addOnDestinationChangedListener { _, dest, _ ->
            binding.bottomNavigationView.visibility = if(dest.id in mainFragmentsId) View.VISIBLE else View.GONE
        }
    }
}