package com.example.bakedeggs.mypage.presentation.viewholders

import android.app.Activity
import com.example.bakedeggs.databinding.MypageItemCardBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.presentation.adapter.MyPageViewPagerAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class CardViewHolder (val binding: MypageItemCardBinding) : MyPageViewHolder(binding), CardSticker {
    override fun bind(
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?, ) {
    }

    override fun bind(
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        activity: Activity
    ) {
        val adapter =
            MyPageViewPagerAdapter(uiModel as MyPageUIModel.CardModel, itemChange, activity)
        binding.mypageVpProfile.adapter = adapter
    }

}