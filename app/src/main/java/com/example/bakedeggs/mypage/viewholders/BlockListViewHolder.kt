package com.example.bakedeggs.mypage.viewholders

import com.example.bakedeggs.databinding.MypageItemBlockListBinding
import com.example.bakedeggs.mypage.MyPageDataObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class BlockListViewHolder(private val binding: MypageItemBlockListBinding): MyPageViewHolder(binding), RemoveListSticker {
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) { }

    override fun bind(
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        position: Int
    ) {
        uiModel as MyPageUIModel.BlockListModel
        binding.mypageTvBlockList.text = uiModel.name
        binding.mypageIvDeleteBlockList.setOnClickListener {
            MyPageDataObj.getDataSource().removeBlock(position)
        }
    }
}