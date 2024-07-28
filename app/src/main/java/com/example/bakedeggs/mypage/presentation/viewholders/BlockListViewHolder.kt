package com.example.bakedeggs.mypage.presentation.viewholders

import com.example.bakedeggs.databinding.MypageItemBlockListBinding
import com.example.bakedeggs.mypage.data.data.MyPageDataObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class BlockListViewHolder(private val binding: MypageItemBlockListBinding): MyPageViewHolder(binding),
    RemoveListSticker {
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) { }

    override fun bind(
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        position: Int
    ) {
        uiModel as MyPageUIModel.BlockListModel
        binding.mypageTvBlockList.text = uiModel.name
        binding.mypageIvDeleteBlockList.setOnClickListener {
            println("qwd $position ${MyPageDataObj.getDataSource().getBlockFirst()}")
            itemChange?.onChangeTag(MyPageDataObj.getDataSource().getData().blackList!![position - MyPageDataObj.getDataSource().getBlockFirst()])
        }
    }
}