package com.example.bakedeggs.data

import android.net.Uri

data class ContactEntity(
    //필수 입력 사항
    var name: String,
    var convertedName: String,
    var num: String,
    var tag: Int, //0일반, 1즐겨찾기, 2차단

    //선택 입력 사항
    var img: Uri?,
    var birth: String?,
    var email: String?,
    var sns: SNS?
) {
    init {
        sns = SNS()
    }
}
