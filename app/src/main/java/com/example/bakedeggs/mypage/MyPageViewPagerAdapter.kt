package com.example.bakedeggs.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.databinding.MypageItemViewpagerBinding
import com.example.bakedeggs.mypage.viewholders.ViewPagerViewHolder

class MyPageViewPagerAdapter : ListAdapter<MyPageViewPagerUIModel, RecyclerView.ViewHolder>(
    MyPageViewPagerDiffUtilCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = MypageItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewPagerViewHolder) holder.bind()
    }

    override fun getItemCount(): Int = 2

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

}