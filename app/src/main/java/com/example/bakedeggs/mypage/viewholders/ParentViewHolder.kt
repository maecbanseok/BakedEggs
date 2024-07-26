package com.example.bakedeggs.mypage.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

abstract class MyPageViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root), ViewBinding by binding {
    abstract fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, )
    // 이거 나중에 abstract로 바꿔야 함
    open fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, activity: MainActivity) { }
}