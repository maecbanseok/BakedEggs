package com.example.bakedeggs.mypage.viewholders

import com.example.bakedeggs.databinding.MypageItemBlockListBinding
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class BlockListViewHolder(private val binding: MypageItemBlockListBinding): MyPageViewHolder(binding) {
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {
        uiModel as MyPageUIModel.BlockListModel
        binding.mypageTvBlockList.text = uiModel.name
    }
}