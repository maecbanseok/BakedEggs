package com.example.bakedeggs.mypage.data

import com.example.bakedeggs.R
import com.example.bakedeggs.data.ContactEntity

data class MyPageDataModel (
    val name: String? = null,
    val phoneNum: String? = null,
    val email: String? = null,
    val photoId: Int? = R.drawable.mypage_base_photo_summer,
    val instagramIds: List<String> = listOf(),
    val githubIds: List<String> = listOf(),
    val discordIds: List<String> = listOf(),
    val favoriteList: List<ContactEntity> = listOf(),
    val blackList: List<ContactEntity> = listOf(),
) {
    fun checkNull(): Boolean {
        var isNull: Boolean = false
        if(name == null || phoneNum == null) {
            isNull = true
        }
        return isNull
    }
}