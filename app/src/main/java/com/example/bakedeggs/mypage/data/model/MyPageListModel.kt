package com.example.bakedeggs.mypage.data.model

data class MyPageSNSListModel(
    val position: Int? = null,
    val index: Int? = null,
    val snsId: String? = null,
    val type: Int? = null,
)

enum class MyPageSNSListEnum(val viewType: Int) {
    INSTAGRAM(0), GITHUB(1), DISCORD(2)
}