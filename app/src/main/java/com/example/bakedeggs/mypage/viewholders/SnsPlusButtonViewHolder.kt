package com.example.bakedeggs.mypage.viewholders

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemSnsPlusButtonBinding
import com.example.bakedeggs.mypage.MyPageDataObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class SnsPlusButtonViewHolder (private val binding: MypageItemSnsPlusButtonBinding) : MyPageViewHolder(binding){
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {
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
            MyPageDataObj.addInsta("insta")
            println("인스타 리스트: ${MyPageDataObj.getData().instagramIds}")
            itemChange?.onChangeData()
        }
        binding.mypageIvSnsGithub.setOnClickListener {
            MyPageDataObj.addGithub("github")
            println("깃헙 리스트: ${MyPageDataObj.getData().githubIds}")
            itemChange?.onChangeData()
        }
        binding.mypageIvSnsDiscord.setOnClickListener {
            MyPageDataObj.addDiscord("discord")
            println("디스코드 리스트: ${MyPageDataObj.getData().discordIds}")
            itemChange?.onChangeData()
        }
    }
}