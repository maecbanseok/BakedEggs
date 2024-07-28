package com.example.bakedeggs.mypage.presentation.viewholders

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bakedeggs.mypage.data.data.MyPageData
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

abstract class MyPageViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root), ViewBinding by binding {
    abstract fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, )
}

interface CardSticker {
    fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, activity: Activity)
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

interface RemoveListSticker {
    fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, position: Int,)
}

interface EmptyCardViewHolder {
    fun bind()
}