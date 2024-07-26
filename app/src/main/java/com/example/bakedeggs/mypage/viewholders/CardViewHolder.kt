package com.example.bakedeggs.mypage.viewholders

import com.example.bakedeggs.databinding.MypageItemCardBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.adapter.MyPageViewPagerAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class CardViewHolder (val binding: MypageItemCardBinding) : MyPageViewHolder(binding){
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, ) { }

    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, activity: MainActivity) {
        val adapter = MyPageViewPagerAdapter(uiModel as MyPageUIModel.CardModel, itemChange, activity)
        binding.mypageVpProfile.adapter = adapter
    }
}