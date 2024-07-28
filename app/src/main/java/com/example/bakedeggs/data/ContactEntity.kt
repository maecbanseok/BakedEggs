package com.example.bakedeggs.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactEntity(
    //필수 입력 사항
    val name: String,
    val convertedName: String,
    val num: String,
    val tag: Int, //0일반, 1즐겨찾기, 2차단

    //선택 입력 사항
    val img: Uri?,
    val birth: String?,
    val email: String?,
    val sns:SNS = SNS()
):Parcelable
