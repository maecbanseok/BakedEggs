package com.example.bakedeggs.mypage.viewholders

import com.example.bakedeggs.databinding.MypageItemFavoriteListBinding
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class FavoriteListViewHolder(private val binding: MypageItemFavoriteListBinding): MyPageViewHolder(binding) {
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {
        uiModel as MyPageUIModel.FavoriteListModel
        binding.mypageTvFavoriteList.text = uiModel.name
        binding.mypageTvFavoriteList.setOnLongClickListener {

            true
        }
    }
}