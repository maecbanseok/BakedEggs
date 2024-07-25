package com.example.bakedeggs.mypage.data

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