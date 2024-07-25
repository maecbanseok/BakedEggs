package com.example.bakedeggs.mypage.viewholders

import com.example.bakedeggs.databinding.MypageItemCardBinding
import com.example.bakedeggs.mypage.MyPageViewPagerAdapter

class CardViewHolder (val binding: MypageItemCardBinding) : MyPageViewHolder(binding){
    override fun bind() {
        val adapter = MyPageViewPagerAdapter()
        binding.mypageVpProfile.adapter = adapter
    }
}