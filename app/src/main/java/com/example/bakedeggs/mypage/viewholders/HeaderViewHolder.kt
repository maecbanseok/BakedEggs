package com.example.bakedeggs.mypage.viewholders

import com.example.bakedeggs.databinding.MypageItemHeaderBinding
import com.example.bakedeggs.mypage.adapter.MyPageRecyclerViewAdapter

class HeaderViewHolder (private val binding: MypageItemHeaderBinding) : MyPageViewHolder(binding){
    override fun bind(itemChange: MyPageRecyclerViewAdapter.ItemChange?,) {
        if(itemChange != null) {
            itemChange.onChangeFold(false, false, false)
        }
    }
}