package com.example.bakedeggs.mypage.data.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.bakedeggs.mypage.data.model.MyPageViewPagerUIModel

class MyPageViewPagerDiffUtilCallback: DiffUtil.ItemCallback<MyPageViewPagerUIModel>() {
    override fun areItemsTheSame(
        oldItem: MyPageViewPagerUIModel,
        newItem: MyPageViewPagerUIModel
    ): Boolean {
        return oldItem.isFront == newItem.isFront
    }

    override fun areContentsTheSame(
        oldItem: MyPageViewPagerUIModel,
        newItem: MyPageViewPagerUIModel
    ): Boolean {
        return oldItem == newItem
    }
}