package com.example.bakedeggs.mypage.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.MainActivity
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemViewpagerBinding
import com.example.bakedeggs.mypage.MyPageDataObj
import com.example.bakedeggs.mypage.adapter.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class ViewPagerViewHolder(private val binding: MypageItemViewpagerBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(uiModel: MyPageUIModel.CardModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?, activity: MainActivity) {
        binding.mypageTvCardName.text = uiModel.name
        binding.mypageTvCardPhoneNum.text = uiModel.phoneNum
        if(!MyPageDataObj.getData().email.isNullOrEmpty())
            binding.mypageTvCardEmail.text = uiModel.email
        if(!MyPageDataObj.getData().instagramIds.isNullOrEmpty())
            binding.mypageTvIconInsta.text = uiModel.instagramId
        if(!MyPageDataObj.getData().githubIds.isNullOrEmpty())
            binding.mypageTvIconGithub.text = uiModel.githubId
        if(!MyPageDataObj.getData().discordIds.isNullOrEmpty())
            binding.mypageTvIconDiscord.text = uiModel.discordId
        binding.mypageIvProfile.setImageResource(uiModel.photoId ?: R.drawable.mypage_base_photo_summer)
        binding.mypageIvProfileBack.setImageResource(uiModel.photoId ?: R.drawable.mypage_base_photo_summer)
        binding.mypageIvCardEdit.setOnClickListener {
            activity.showAddCardDialog(itemChange)
        }
    }
}