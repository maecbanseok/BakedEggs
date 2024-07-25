package com.example.bakedeggs.mypage.data.model

data class MyPageViewPagerUIModel(
    val photoId: Int? = null,
    val name: String? = null,
    val phoneNum: String? = null,
    val email: String? = null,

    val instagramIconId: Int? = null,
    val instagramId: String? = null,

    val githubIconId: Int? = null,
    val githubId: String? = null,

    val discordIconId: Int? = null,
    val discordId: String? = null,
    val isFront: Boolean = true,
)