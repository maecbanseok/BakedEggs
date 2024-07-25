package com.example.bakedeggs.mypage.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.MainActivity
import com.example.bakedeggs.databinding.MypageItemCardBinding
import com.example.bakedeggs.mypage.adapter.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.adapter.MyPageViewPagerAdapter

class CardViewHolder (val binding: MypageItemCardBinding) : MyPageViewHolder(binding){
    override fun bind(itemClick: MyPageRecyclerViewAdapter.ItemChange?,) { }

    override fun bind(itemClick: MyPageRecyclerViewAdapter.ItemChange?, activity: MainActivity) {
        val adapter = MyPageViewPagerAdapter(itemClick, activity)
        binding.mypageVpProfile.adapter = adapter
    }
}