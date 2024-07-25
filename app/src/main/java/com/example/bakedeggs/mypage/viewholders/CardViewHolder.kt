package com.example.bakedeggs.mypage.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.MainActivity
import com.example.bakedeggs.databinding.MypageItemCardBinding
import com.example.bakedeggs.mypage.adapter.MyPageViewPagerAdapter

class CardViewHolder (val binding: MypageItemCardBinding) : MyPageViewHolder(binding){
    override fun bind() { }

    override fun bind(activity: MainActivity) {
        val adapter = MyPageViewPagerAdapter(activity)
        binding.mypageVpProfile.adapter = adapter
    }
}