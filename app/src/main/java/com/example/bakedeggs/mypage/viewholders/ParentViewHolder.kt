package com.example.bakedeggs.mypage.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.bakedeggs.main.MainActivity

abstract class MyPageViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root), ViewBinding by binding {
    abstract fun bind()
    open fun bind(activity: MainActivity) { }
}