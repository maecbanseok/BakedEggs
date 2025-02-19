package com.example.bakedeggs.mypage.presentation

import com.example.bakedeggs.R
import com.example.bakedeggs.mypage.data.data.MyPageFlagObj
import com.example.bakedeggs.mypage.data.model.MyPageDataModel
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

fun MyPageDataModel.makeMyPageUIList(): List<MyPageUIModel> {

    var idCount = 0

    println("카드 모델 ${this.snsIds}")
    println("카드 모델 ${this.snsIds?.find { it.type == 0 }?.snsId ?: ""}")

    val list: MutableList<MyPageUIModel> = mutableListOf(
        MyPageUIModel.TopBarModel(
            id = idCount++
        ),
        MyPageUIModel.CardModel(
            id = idCount++,
            photoId = this.photoId,
            name = this.name,
            phoneNum = this.phoneNum,
            email = this.email,
            instagramId = this.snsIds?.find { it.type == 0 }?.snsId ?: "",
            githubId = this.snsIds?.find { it.type == 1 }?.snsId  ?: "",
            discordId = this.snsIds?.find { it.type == 2 }?.snsId  ?: "",
        ),
    )

    val snsListSize = this.snsIds?.size ?: 0

    if (MyPageFlagObj.getFlag().isOpenSNS) {
        list.add(
            MyPageUIModel.HeaderModel(
                id = idCount++,
                title = "SNS 계정",
                type = 0,
                isFold = false
            )
        )
        for (element in this.snsIds ?: listOf()) {
            when(element.type) {
                0 -> {
                    list.add(
                        MyPageUIModel.ListModel(
                            id = idCount++,
                            iconId = R.drawable.mypage_icon_insta,
                            content = element.snsId,
                            type = 0,
                        )
                    )
                }
                1 -> {
                    list.add(
                        MyPageUIModel.ListModel(
                            id = idCount++,
                            iconId = R.drawable.mypage_icon_github,
                            content = element.snsId,
                            type = 1,
                        )
                    )
                }
                2 -> {
                    list.add(
                        MyPageUIModel.ListModel(
                            id = idCount++,
                            iconId = R.drawable.mypage_icon_discord,
                            content = element.snsId,
                            type = 2,
                        )
                    )
                }
                else -> {

                }
            }

        }
        if (snsListSize < 9) {
            list.add(
                MyPageUIModel.SnsPlusButtonModel(
                    id = idCount++,
                )
            )
        }
    } else {
        list.add(
            MyPageUIModel.HeaderModel(
                id = idCount++,
                title = "SNS 계정",
                type = 0,
                isFold = true
            )
        )
    }

    if (MyPageFlagObj.getFlag().isOpenFavorite) {
        list.add(
            MyPageUIModel.HeaderModel(
                id = idCount++,
                title = "즐겨찾기 목록",
                type = 1,
                isFold = false,
            )
        )
        println("얘맞지 ${this.favoriteList}")
        for (element in this.favoriteList ?: listOf()) {
            list.add(
                MyPageUIModel.FavoriteListModel(
                    id = idCount++,
                    name = element.name
                )
            )
        }
    } else {
        list.add(
            MyPageUIModel.HeaderModel(
                id = idCount++,
                title = "즐겨찾기 목록",
                type = 1,
                isFold = true,
            )
        )
    }

    if (MyPageFlagObj.getFlag().isOpenBlock) {
        list.add(
            MyPageUIModel.HeaderModel(
                id = idCount++,
                title = "차단 목록",
                type = 2,
                isFold = false
            )
        )
        for (element in this.blackList ?: listOf()) {
            list.add(
                MyPageUIModel.BlockListModel(
                    id = idCount++,
                    name = element.name.ifEmpty { element.num }
                )
            )
        }
    } else {
        list.add(
            MyPageUIModel.HeaderModel(
                id = idCount++,
                title = "차단 목록",
                type = 2,
                isFold = true
            )
        )
    }

    return list
}