package com.example.bakedeggs.mypage.data.changer

import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.mypage.MyPageDataObj

enum class TAG {
    NORMAL, FAVORITE, BLOCK
}

fun ArrayList<ContactEntity>.extractToMyPageDataObj() {

    this.forEach {
        val entityTag = TAG.entries.find { tag ->
            tag.ordinal == it.tag
        }
        when(entityTag) {
            TAG.FAVORITE -> {
                MyPageDataObj.addFavorite(it)
            }
            TAG.BLOCK -> {
                MyPageDataObj.addBlock(it)
            }
            TAG.NORMAL -> {

            }
            null -> {

            }
        }
    }
}