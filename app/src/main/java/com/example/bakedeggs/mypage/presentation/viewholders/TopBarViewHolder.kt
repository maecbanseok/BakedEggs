package com.example.bakedeggs.mypage.presentation.viewholders

import android.app.Activity
import android.media.MediaPlayer
import android.net.Uri
import android.widget.MediaController
import com.example.bakedeggs.databinding.ActivityDetailBinding
import com.example.bakedeggs.databinding.MypageItemTopBarBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import kotlin.random.Random

class TopBarViewHolder(private val binding: MypageItemTopBarBinding) : MyPageViewHolder(binding),
    CardSticker {
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
        var uri: Uri = adList[Random.nextInt(adList.size)]
        val mediaController = MediaController(activity)
        mediaController.setAnchorView(binding.vvAds)
        binding.vvAds.setMediaController(mediaController)
        binding.vvAds.setVideoURI(uri)
        binding.vvAds.requestFocus()
        val completeListener = MediaPlayer.OnCompletionListener {
            uri = adList[Random.nextInt(adList.size)]
            binding.vvAds.setVideoURI(uri)
            binding.vvAds.start()
        }
        binding.vvAds.setOnCompletionListener(completeListener)
        binding.vvAds.start()
    }
}