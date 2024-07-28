package com.example.bakedeggs.mypage.viewholders

import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemListBinding
import com.example.bakedeggs.databinding.MypageItemUneditableListBinding
import com.example.bakedeggs.mypage.MyPageData
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class UneditableListViewHolder (private val binding: MypageItemUneditableListBinding) : MyPageViewHolder(binding){
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {
        uiModel as MyPageUIModel.ListModel
        binding.mypageIvUneditableListSns.setImageResource(uiModel.iconId ?: R.drawable.mypage_base_photo_summer)
        binding.mypageTvUneditableListSns.text = uiModel.content
    }

//    override fun bind(
//        data: MyPageData?,
//        uiModel: MyPageUIModel,
//        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
//        isEditable: Boolean,
//        position: Int
//    ) {
//
//    }
}