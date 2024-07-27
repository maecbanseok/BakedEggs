package com.example.bakedeggs.mypage

data class MyPageFlag(
    val isOpenSNS: Boolean = true,
    val isOpenFavorite: Boolean = true,
    val isOpenBlock: Boolean = false,
    val isListEditable: Boolean = true,
)
