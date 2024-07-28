package com.example.bakedeggs.mypage.presentation.viewholders

import com.example.bakedeggs.databinding.MypageItemFavoriteListBinding
import com.example.bakedeggs.mypage.data.data.MyPageDataObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class FavoriteListViewHolder(private val binding: MypageItemFavoriteListBinding): MyPageViewHolder(binding),
    RemoveListSticker {
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) { }

    override fun bind(
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        position: Int
    ) {
        uiModel as MyPageUIModel.FavoriteListModel
        binding.mypageTvFavoriteList.text = uiModel.name
        binding.mypageIvDeleteFavoriteList.setOnClickListener {
            println("qwd $position ${MyPageDataObj.getDataSource().getFavoriteFirst()}")
            itemChange?.onChangeTag(MyPageDataObj.getDataSource().getData().favoriteList!![position - MyPageDataObj.getDataSource().getFavoriteFirst()])
        }
        binding.mypageTvFavoriteList.setOnClickListener {
            val index = position - MyPageDataObj.getDataSource().getFavoriteFirst()
            itemChange?.onChangeFragment(index)
        }
    }
}