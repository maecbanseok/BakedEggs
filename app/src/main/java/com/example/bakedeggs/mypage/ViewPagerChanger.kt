package com.example.bakedeggs.mypage

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