package com.example.bakedeggs.mypage

sealed class MyPageUIModel {
    abstract val id: Int?
    data class TopBarModel(
        override val id: Int = 0,
        val iconId: Int? = null,
        val videoId: Int? = null,
    ): MyPageUIModel()
    data class HeaderModel(
        override val id: Int?,
        val title: String?,
    ): MyPageUIModel()
    data class ListModel(
        override val id: Int?,
        val iconId: Int?,
        val content: String?
    ): MyPageUIModel(), ListElement
    data object SnsPlusButtonModel: MyPageUIModel(), ListElement {
        override val id: Int? = null
    }
    data class CardModel(
        override val id: Int = 1,
        val photoId: Int? = null,
        val name: String? = null,
        val phoneNum: String? = null,
        val email: String? = null,

        val instagramIconId: Int? = null,
        val instagramId: String? = null,

        val githubIconId: Int? = null,
        val githubId: String? = null,

        val discordIconId: Int? = null,
        val discordId: String? = null,
    ) : MyPageUIModel()


    data class EmptyModel(override val id: Int?) : MyPageUIModel()
}

interface ListElement