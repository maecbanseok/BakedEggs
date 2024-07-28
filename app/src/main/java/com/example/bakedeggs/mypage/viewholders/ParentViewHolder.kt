package com.example.bakedeggs.mypage.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.MyPageData
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

abstract class MyPageViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root), ViewBinding by binding {
    abstract fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, )
}

interface CardSticker {
    fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, activity: MainActivity)
}

interface ListSticker {
    fun bind(data: MyPageData?, uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, isEditable: Boolean, position: Int, count: Int)
}

interface EditableListAddSticker {
    fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, index: Int)
}

interface SnsPlusButtonSticker {
    fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, position: Int, count: Int)
}

interface EmptyCardViewHolder {
    fun bind()
}