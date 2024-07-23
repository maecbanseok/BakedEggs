package com.example.bakedeggs.mypage

import androidx.recyclerview.widget.DiffUtil

class MyPageDiffUtilCallback: DiffUtil.ItemCallback<MyPageUIModel>() {
    override fun areItemsTheSame(oldItem: MyPageUIModel, newItem: MyPageUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem:MyPageUIModel, newItem: MyPageUIModel): Boolean {
        return oldItem == newItem
    }
}