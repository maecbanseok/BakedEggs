package com.example.bakedeggs.mypage

import com.example.bakedeggs.R
import com.example.bakedeggs.mypage.data.MyPageDataModel
import com.example.bakedeggs.mypage.data.MyPageUIModel

fun makeMyPageUIList(data: MyPageDataModel, isOpenSNS: Boolean = true, isOpenFavorite: Boolean = true, isOpenBlock: Boolean = false): List<MyPageUIModel> {

    var idCount = 0

    val list: MutableList<MyPageUIModel> = mutableListOf(
        MyPageUIModel.TopBarModel(
            id = idCount++
        ),
        MyPageUIModel.CardModel(
            id = idCount++,
            photoId = data.photoId,
            name = data.name,
            phoneNum = data.phoneNum,
            email = data.email,
            instagramId = data.instagramIds?.get(0) ?: "",
            githubId = data.githubIds?.get(0) ?: "",
            discordId = data.discordIds?.get(0) ?: "",
        ),
        MyPageUIModel.HeaderModel(
            id = idCount++,
            title = "SNS 계정",
        ),
    )

    val snsListSize = (data.instagramIds?.size ?: 0 )+ (data.githubIds?.size ?: 0) + (data.discordIds?.size ?: 0)

    if (isOpenSNS) {
        for (element in data.instagramIds ?: listOf()) {
            list.add(
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_icon_insta,
                    content = element
                )
            )
        }
        for (element in data.githubIds ?: listOf()) {
            list.add(
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_icon_github,
                    content = element
                )
            )
        }
        for (element in data.discordIds ?: listOf()) {
            list.add(
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_icon_discord,
                    content = element
                )
            )
        }
        if (snsListSize < 9) {
            list.add(
                MyPageUIModel.SnsPlusButtonModel.apply {
                    id = idCount++
                }
            )
        }
    }

    list.add(
        MyPageUIModel.HeaderModel(
            id = idCount++,
            title = "즐겨찾기 목록",
        )
    )

    if (isOpenFavorite) {
        for (element in data.favoriteList ?: listOf()) {
            list.add(
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_baseline_star_24,
                    content = element.name
                )
            )
        }
    }

    list.add(
        MyPageUIModel.HeaderModel(
            id = idCount++,
            title = "차단 목록",
        )
    )

    if (isOpenBlock) {
        for (element in data.blackList ?: listOf()) {
            list.add(
                MyPageUIModel.ListModel(
                    id = idCount++,
                    iconId = R.drawable.mypage_baseline_block_24,
                    content = element.num
                )
            )
        }
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