package com.example.bakedeggs.mypage.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.bakedeggs.mypage.MyPageUIModel

class MyPageDiffUtilCallback: DiffUtil.ItemCallback<MyPageUIModel>() {
    override fun areItemsTheSame(oldItem: MyPageUIModel, newItem: MyPageUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MyPageUIModel, newItem: MyPageUIModel): Boolean {
        return oldItem == newItem
    }
}