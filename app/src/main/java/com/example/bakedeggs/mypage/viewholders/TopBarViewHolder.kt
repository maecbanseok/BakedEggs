package com.example.bakedeggs.mypage.viewholders

import android.app.Activity
import android.net.Uri
import com.example.bakedeggs.databinding.MypageItemTopBarBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import kotlin.random.Random

class TopBarViewHolder(private val binding: MypageItemTopBarBinding) : MyPageViewHolder(binding), CardSticker {
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {}

    override fun bind(
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        activity: Activity
    ) {
        val adList = listOf<Uri>(
            Uri.parse("android.resource://" + activity.packageName + "/raw/ad1"),
            Uri.parse("android.resource://" + activity.packageName + "/raw/ad2"),
            Uri.parse("android.resource://" + activity.packageName + "/raw/ad3"),
            Uri.parse("android.resource://" + activity.packageName + "/raw/ad4"),
            Uri.parse("android.resource://" + activity.packageName + "/raw/ad5"),
        )

        val uri: Uri = adList[Random.nextInt(adList.size)]
        binding.vvAds.setVideoURI(uri)
    }
}