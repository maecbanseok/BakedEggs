package com.example.bakedeggs.mypage.data.model

import android.net.Uri
import com.example.bakedeggs.R
import com.example.bakedeggs.data.ContactEntity
import java.net.URI

data class MyPageDataModel (
    val name: String? = null,
    val phoneNum: String? = null,
    val email: String? = null,
    val photoId: Uri? = null,
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