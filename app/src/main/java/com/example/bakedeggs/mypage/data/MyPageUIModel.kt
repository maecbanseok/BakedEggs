package com.example.bakedeggs.mypage.data

import com.example.bakedeggs.R

sealed class MyPageUIModel {

    abstract val id: Int?

    data class TopBarModel(
        override val id: Int = 0,
        val iconId: Int? = R.mipmap.ic_launcher_foreground,
        val videoId: Int? = null,
    ): MyPageUIModel()

    data class HeaderModel(
        override val id: Int? = null,
        val title: String? = null,
    ): MyPageUIModel()

    data class ListModel(
        override val id: Int? = null,
        val iconId: Int? = null,
        val content: String? = null,
    ): MyPageUIModel(), ListElement

    data object SnsPlusButtonModel: MyPageUIModel(), ListElement {
        override var id: Int? = null
    }

    data class CardModel(
        override val id: Int = 1,
        val photoId: Int? = null,
        val name: String? = null,
        val phoneNum: String? = null,
        val email: String? = null,

        val instagramIconId: Int? = R.drawable.mypage_icon_insta,
        val instagramId: String? = null,

        val githubIconId: Int? = R.drawable.mypage_icon_github,
        val githubId: String? = null,

        val discordIconId: Int? = R.drawable.mypage_icon_discord,
        val discordId: String? = null,
    ) : MyPageUIModel()

    data class EmptyModel(override val id: Int? = null) : MyPageUIModel()
}

interface ListElement