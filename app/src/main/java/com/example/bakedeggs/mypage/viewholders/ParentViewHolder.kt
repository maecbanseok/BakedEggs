package com.example.bakedeggs.mypage.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bakedeggs.MainActivity
import com.example.bakedeggs.mypage.adapter.MyPageRecyclerViewAdapter

abstract class MyPageViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root), ViewBinding by binding {
    abstract fun bind(itemChange: MyPageRecyclerViewAdapter.ItemChange?)
    open fun bind(itemChange: MyPageRecyclerViewAdapter.ItemChange?, activity: MainActivity) { }
}