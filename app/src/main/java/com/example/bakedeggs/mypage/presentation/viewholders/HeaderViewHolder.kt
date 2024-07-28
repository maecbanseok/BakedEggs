package com.example.bakedeggs.mypage.presentation.viewholders

import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemHeaderBinding
import com.example.bakedeggs.mypage.data.data.MyPageFlagObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class HeaderViewHolder (private val binding: MypageItemHeaderBinding) : MyPageViewHolder(binding){
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {
        uiModel as MyPageUIModel.HeaderModel
        binding.mypageTvListTitle.text = uiModel.title
        binding.mypageBtnListTitleFold.setOnClickListener {
            when(uiModel.type) {
                0 -> {
                    MyPageFlagObj.switchSnsOpen()
                    println("변경1")
                }
                1 -> {
                    MyPageFlagObj.switchFavoriteOpen()
                    println("변경2")
                }
                2 -> {
                    MyPageFlagObj.switchBlockOpen()
                    println("변경3")
                }
            }
            itemChange?.onChangeData()
        }
        if(uiModel.isFold) {
            binding.mypageBtnListTitleFold.setImageResource(R.drawable.baseline_arrow_drop_down_24)
        } else {
            binding.mypageBtnListTitleFold.setImageResource(R.drawable.baseline_arrow_drop_up_24)
        }
    }
}