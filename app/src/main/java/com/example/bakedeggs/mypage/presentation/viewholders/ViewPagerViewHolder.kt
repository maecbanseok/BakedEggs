package com.example.bakedeggs.mypage.presentation.viewholders

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat.startActivity
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
        var sns = "instagram.com/"
        binding.mypageTvCardName.text = uiModel.name
        binding.mypageTvCardPhoneNum.text = uiModel.phoneNum
        binding.mypageTvCardEmail.text = uiModel.email
        binding.mypageTvIconInsta.text = uiModel.instagramId
        binding.mypageEfvCard.setOnClickListener {
            if(binding.mypageTvIconInsta.visibility == VISIBLE) {
                binding.mypageTvIconInsta.visibility = INVISIBLE
                binding.mypageTvIconGithub.visibility = INVISIBLE
                binding.mypageTvIconDiscord.visibility = INVISIBLE
                binding.mypageIvIconGithub.visibility = INVISIBLE
                binding.mypageIvIconInsta.visibility = INVISIBLE
                binding.mypageIvIconInsta.visibility = INVISIBLE
            } else {
                binding.mypageTvIconInsta.visibility = VISIBLE
                binding.mypageTvIconGithub.visibility = VISIBLE
                binding.mypageTvIconDiscord.visibility = VISIBLE
                binding.mypageIvIconGithub.visibility = VISIBLE
                binding.mypageIvIconInsta.visibility = VISIBLE
                binding.mypageIvIconInsta.visibility = VISIBLE
            }
        }
        binding.mypageTvIconInsta.setOnClickListener {
            sns = "instagram.com/"
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.$sns")))
        }
        binding.mypageTvIconGithub.text = uiModel.githubId
        binding.mypageTvIconGithub.setOnClickListener {
            sns = "github.com/"
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.$sns")))
        }
        binding.mypageTvIconDiscord.text = uiModel.discordId
        binding.mypageTvIconDiscord.setOnClickListener {
            sns = "discordapp.com/users/"
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.$sns")))
        }
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