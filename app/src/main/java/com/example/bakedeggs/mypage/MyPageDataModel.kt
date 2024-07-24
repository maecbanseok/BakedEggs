package com.example.bakedeggs.mypage

data class MyPageDataModel (
    val name: String? = null,
    val phoneNum: String? = null,
    val email: String? = null,
    val instagramIds: ArrayList<String> = arrayListOf(),
    val githubIds: ArrayList<String> = arrayListOf(),
    val discordIds: ArrayList<String> = arrayListOf(),
)