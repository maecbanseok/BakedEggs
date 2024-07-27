package com.example.bakedeggs.mypage.data.model

import com.example.bakedeggs.R
import com.example.bakedeggs.data.ContactEntity

data class MyPageDataModel (
    val name: String? = null,
    val phoneNum: String? = null,
    val email: String? = null,
    val photoId: Int? = R.drawable.mypage_base_photo_summer,
    val snsIds: List<MyPageSNSListModel>? = null,
    val favoriteList: List<ContactEntity>? = null,
    val blackList: List<ContactEntity>? = null,
    val uiModelList: List<MyPageUIModel>? = null,
) {
    fun checkNull(): Boolean {
        var isNull: Boolean = false
        if(name == null || phoneNum == null) {
            isNull = true
        }
        return isNull
    }
}