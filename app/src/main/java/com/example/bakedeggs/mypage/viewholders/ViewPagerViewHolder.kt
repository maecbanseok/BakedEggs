package com.example.bakedeggs.mypage.viewholders

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemViewpagerBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.MyPageDataObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class ViewPagerViewHolder(private val binding: MypageItemViewpagerBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        uiModel: MyPageUIModel.CardModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        activity: Activity
    ) {
        binding.mypageTvCardName.text = uiModel.name
        binding.mypageTvCardPhoneNum.text = uiModel.phoneNum
        binding.mypageTvCardEmail.text = uiModel.email
        binding.mypageTvIconInsta.text = uiModel.instagramId
        binding.mypageTvIconGithub.text = uiModel.githubId
        binding.mypageTvIconDiscord.text = uiModel.discordId
        binding.mypageIvProfile.setImageResource(
            uiModel.photoId ?: R.drawable.mypage_base_photo_summer
        )
        binding.mypageIvProfileBack.setImageResource(
            uiModel.photoId ?: R.drawable.mypage_base_photo_summer
        )
        binding.mypageIvCardEdit.setOnClickListener {
            activity.showAddCardDialog(itemChange)
        }
    }
}