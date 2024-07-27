package com.example.bakedeggs.mypage.data.model

import com.example.bakedeggs.R

sealed class MyPageUIModel {

    abstract val id: Int?

    data class EmptyModel(override val id: Int? = null) : MyPageUIModel()

    data class TopBarModel(
        override val id: Int = 0,
        val iconId: Int? = R.mipmap.ic_launcher_foreground,
        val videoId: Int? = null,
    ): MyPageUIModel()

    data class HeaderModel(
        override val id: Int? = null,
        val title: String? = null,
        val type: Int? = null,
        val isFold: Boolean = false,
    ): MyPageUIModel()

    data class ListModel(
        override val id: Int? = null,
        val iconId: Int? = null,
        val content: String? = null,
        val type: Int? = null,
        val isEditable: Boolean = true,
    ): MyPageUIModel()

    data class SnsPlusButtonModel(
        override val id: Int? = null,
    ): MyPageUIModel()

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

    data class FavoriteListModel(
        override val id: Int?
    ): MyPageUIModel()

    data class BlockListModel(
        override val id: Int?
    ): MyPageUIModel()
}