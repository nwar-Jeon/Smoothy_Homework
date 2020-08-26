package com.nwar.smoothy_homework.presenter.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nwar.smoothy_homework.presenter.ui.fragment.FavoriteFragment
import com.nwar.smoothy_homework.presenter.ui.fragment.MainFragment
import java.lang.IndexOutOfBoundsException

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return MainFragment()
            1 -> return FavoriteFragment()
            else -> throw IndexOutOfBoundsException("ViewPager Index Error")
        }
    }

    override fun getItemCount(): Int = 2
}