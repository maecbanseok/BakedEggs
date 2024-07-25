package com.example.bakedeggs.mypage.viewholders

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemSnsPlusButtonBinding
import com.example.bakedeggs.mypage.MyPageDataObj
import com.example.bakedeggs.mypage.adapter.MyPageRecyclerViewAdapter

class SnsPlusButtonViewHolder (private val binding: MypageItemSnsPlusButtonBinding) : MyPageViewHolder(binding){
    override fun bind(itemChange: MyPageRecyclerViewAdapter.ItemChange?,) {
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
            MyPageDataObj.addInsta("")
            if(itemChange != null) itemChange.onChangeData()
        }
        binding.mypageIvSnsGithub.setOnClickListener {
            MyPageDataObj.addGithub("")
            if(itemChange != null) itemChange.onChangeData()
        }
        binding.mypageIvSnsDiscord.setOnClickListener {
            MyPageDataObj.addDiscord("")
            if(itemChange != null) itemChange.onChangeData()
        }
    }
}