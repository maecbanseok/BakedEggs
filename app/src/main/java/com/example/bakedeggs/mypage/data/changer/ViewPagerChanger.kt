package com.example.bakedeggs.mypage.data.changer

import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import com.example.bakedeggs.mypage.data.model.MyPageViewPagerUIModel

fun MyPageUIModel.CardModel.asViewPagerModel(): MyPageViewPagerUIModel {
    return MyPageViewPagerUIModel(
        photoId,
        name,
        phoneNum,
        email,
        instagramIconId,
        instagramId,
        githubIconId,
        githubId,
        discordIconId,
        discordId
    )
}