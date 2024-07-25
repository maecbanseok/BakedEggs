package com.example.bakedeggs.mypage.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.databinding.MypageItemViewpagerBinding
import com.example.bakedeggs.mypage.MyPageDataObj

class ViewPagerViewHolder(private val binding: MypageItemViewpagerBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.mypageTvCardName.text = MyPageDataObj.getData().name
        binding.mypageTvCardPhoneNum.text = MyPageDataObj.getData().phoneNum
        if(!MyPageDataObj.getData().email.isNullOrEmpty())
            binding.mypageTvCardEmail.text = MyPageDataObj.getData().email
        if(!MyPageDataObj.getData().instagramIds.isNullOrEmpty())
            binding.mypageTvIconInsta.text = MyPageDataObj.getData().instagramIds?.get(0) ?: ""
        if(!MyPageDataObj.getData().githubIds.isNullOrEmpty())
            binding.mypageTvIconGithub.text = MyPageDataObj.getData().githubIds?.get(0) ?: ""
        if(!MyPageDataObj.getData().discordIds.isNullOrEmpty())
            binding.mypageTvIconDiscord.text = MyPageDataObj.getData().discordIds?.get(0) ?: ""
        binding.mypageIvProfile.setImageResource(MyPageDataObj.getData().photoId!!)
    }
}