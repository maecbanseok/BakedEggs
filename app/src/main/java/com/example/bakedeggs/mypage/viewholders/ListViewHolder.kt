package com.example.bakedeggs.mypage.viewholders

import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemListBinding
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class ListViewHolder (private val binding: MypageItemListBinding) : MyPageViewHolder(binding){
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {
        uiModel as MyPageUIModel.ListModel
        binding.mypageIvListSns.setImageResource(uiModel.iconId ?: R.drawable.mypage_base_photo_summer)
        binding.mypageEtListSns.setText(uiModel.content)
    }
}