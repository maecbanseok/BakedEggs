package com.example.bakedeggs.mypage.presentation.viewholders

import android.app.Activity
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.databinding.MypageItemViewpagerBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.presentation.MyPageAddCardFragment
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
        binding.mypageIvProfile.setImageURI(
            uiModel.photoId
        )
        binding.mypageIvProfileBack.setImageURI(
            uiModel.photoId
        )
        binding.mypageIvCardEdit.setOnClickListener {
            MyPageAddCardFragment(itemChange!!).show((activity as MainActivity).supportFragmentManager, "Card Add Contact")
        }
    }
}