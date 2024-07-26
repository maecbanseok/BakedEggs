package com.example.bakedeggs.mypage

import com.example.bakedeggs.R
import com.example.bakedeggs.mypage.data.model.MyPageDataModel
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

fun MyPageDataModel.makeMyPageUIList(): List<MyPageUIModel> {

    var idCount = 0

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
            instagramId = this.instagramIds?.get(0) ?: "",
            githubId = this.githubIds?.get(0) ?: "",
            discordId = this.discordIds?.get(0) ?: "",
        ),
    )

    val snsListSize = (this.instagramIds?.size ?: 0 )+ (this.githubIds?.size ?: 0) + (this.discordIds?.size ?: 0)

    if (MyPageFlagObj.getFlag().isOpenSNS) {
        list.add(
            MyPageUIModel.HeaderModel(
                id = idCount++,
                title = "SNS 계정",
                type = 0,
                isFold = false
            )
        )
        for (element in this.instagramIds ?: listOf()) {
            list.add(
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_icon_insta,
                    content = element,
                    type = 0,
                )
            )
        }
        for (element in this.githubIds ?: listOf()) {
            list.add(
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_icon_github,
                    content = element,
                    type = 1,
                )
            )
        }
        for (element in this.discordIds ?: listOf()) {
            list.add(
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_icon_discord,
                    content = element,
                    type = 2,
                )
            )
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
        for (element in this.favoriteList ?: listOf()) {
            list.add(
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_baseline_star_24,
                    content = element.name
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
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_baseline_block_24,
                    content = element.num
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



//    val snsAccountList: List<ListElement> = listOf()
//    val favoriteList: List<ListElement> = listOf()
//    val blackList: List<ListElement> = listOf()
//
//    fun getSnsListRange(): IntRange {
//        val start = 3
//        return getListRange(start, snsAccountList)
//    }
//
//    fun getFavoriteListRange(): IntRange {
//        val start = getSnsListRange().last + 2
//        return getListRange(start, favoriteList)
//    }
//
//    fun getBlackListRange(): IntRange {
//        val start = getFavoriteListRange().last + 2
//        return getListRange(start, blackList)
//    }
//
//    fun getListRange(start: Int, list: List<ListElement>): IntRange = (start..<list.size + start)