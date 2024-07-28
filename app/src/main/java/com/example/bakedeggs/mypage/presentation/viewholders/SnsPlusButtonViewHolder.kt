package com.example.bakedeggs.mypage.presentation.viewholders

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.view.isVisible
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemSnsPlusButtonBinding
import com.example.bakedeggs.mypage.data.data.MyPageDataObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageSNSListEnum
import com.example.bakedeggs.mypage.data.model.MyPageSNSListModel
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class SnsPlusButtonViewHolder (private val binding: MypageItemSnsPlusButtonBinding) : MyPageViewHolder(binding),
    SnsPlusButtonSticker {
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {}

    override fun bind(
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        position: Int,
        count: Int
    ) {
        uiModel as MyPageUIModel.SnsPlusButtonModel
        binding.mypageIvSnsFunction.setOnClickListener {
            if(binding.mypageIvSnsInsta.isVisible) {
                binding.mypageIvSnsFunction.setImageResource(R.drawable.mypage_icon_plus)
                binding.mypageIvSnsInsta.visibility = INVISIBLE
                binding.mypageIvSnsGithub.visibility = INVISIBLE
                binding.mypageIvSnsDiscord.visibility = INVISIBLE
            } else {
                binding.mypageIvSnsFunction.setImageResource(R.drawable.mypage_icon_minus)
                binding.mypageIvSnsInsta.visibility = VISIBLE
                binding.mypageIvSnsGithub.visibility = VISIBLE
                binding.mypageIvSnsDiscord.visibility = VISIBLE
            }
        }
        binding.mypageIvSnsInsta.setOnClickListener {
            MyPageDataObj.getDataSource().addSns(MyPageSNSListModel(type = MyPageSNSListEnum.INSTAGRAM.viewType, snsId = "", position = position), position)
            itemChange?.onChangeData()
        }
        binding.mypageIvSnsGithub.setOnClickListener {
            MyPageDataObj.getDataSource().addSns(MyPageSNSListModel(type = MyPageSNSListEnum.GITHUB.viewType, snsId = "", position = position), position)
            itemChange?.onChangeData()
        }
        binding.mypageIvSnsDiscord.setOnClickListener {
            MyPageDataObj.getDataSource().addSns(MyPageSNSListModel(type = MyPageSNSListEnum.DISCORD.viewType, snsId = "", position = position), position)
            itemChange?.onChangeData()
        }
//        binding.mypageIvSnsInsta.setOnFocusChangeListener { view: View, focus: Boolean ->
//            if(focus) {
//                binding.mypageIvSnsInsta.performClick()
//            }
//        }
//        binding.mypageIvSnsGithub.setOnFocusChangeListener { view: View, focus: Boolean ->
//            if(focus) {
//                binding.mypageIvSnsGithub.performClick()
//            }
//        }
//        binding.mypageIvSnsDiscord.setOnFocusChangeListener { view: View, focus: Boolean ->
//            if(focus) {
//                binding.mypageIvSnsDiscord.performClick()
//            }
//        }
    }
}