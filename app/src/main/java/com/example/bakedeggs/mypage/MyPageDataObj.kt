package com.example.bakedeggs.mypage

import android.app.Application
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.mypage.data.MyPageDataModel
import com.example.bakedeggs.mypage.data.MyPageUIModel
import com.example.bakedeggs.mypage.data.extractToMyPageDataObj

object MyPageDataObj {
    private var myPageData = MyPageDataModel()

    fun initData(application: Application) {
//        myPageData = data
        val serviceLocator = ServiceLocator(application)
        serviceLocator.contactRepositoryImpl.getContactList().extractToMyPageDataObj()
    }

    fun addFavorite(entity: ContactEntity) {
        val favoriteList: MutableList<ContactEntity> = myPageData.favoriteList?.toMutableList() ?: mutableListOf<ContactEntity>()
        favoriteList.add(entity)
        myPageData = MyPageDataModel(
            myPageData.name,
            myPageData.phoneNum,
            myPageData.email,
            myPageData.photoId,
            myPageData.instagramIds,
            myPageData.githubIds,
            myPageData.discordIds,
            favoriteList,
            myPageData.blackList
        )
    }

    fun addBlock(entity: ContactEntity) {
        val blackList: MutableList<ContactEntity> = myPageData.blackList?.toMutableList() ?: mutableListOf<ContactEntity>()
        blackList.add(entity)
        myPageData = MyPageDataModel(
            myPageData.name,
            myPageData.phoneNum,
            myPageData.email,
            myPageData.photoId,
            myPageData.instagramIds,
            myPageData.githubIds,
            myPageData.discordIds,
            myPageData.favoriteList,
            blackList
        )
    }

    fun addNewProfile(data: MyPageUIModel.CardModel) {
        myPageData = MyPageDataModel(
            data.name,
            data.phoneNum,
            data.email,
            data.photoId,
            myPageData.instagramIds,
            myPageData.githubIds,
            myPageData.discordIds,
            myPageData.favoriteList,
            myPageData.blackList,
        )
    }

    fun addInsta(data: String?) {
        val instaList = myPageData.instagramIds?.toMutableList() ?: mutableListOf()
        if(data != null) instaList.add(data)
        myPageData = MyPageDataModel(
            myPageData.name,
            myPageData.phoneNum,
            myPageData.email,
            myPageData.photoId,
            instaList,
            myPageData.githubIds,
            myPageData.discordIds,
            myPageData.favoriteList,
            myPageData.blackList,
        )
    }

    fun addGithub(data: String?) {
        val githubList = myPageData.instagramIds?.toMutableList() ?: mutableListOf()
        if(data != null) githubList.add(data)
        myPageData = MyPageDataModel(
            myPageData.name,
            myPageData.phoneNum,
            myPageData.email,
            myPageData.photoId,
            myPageData.instagramIds,
            githubList,
            myPageData.discordIds,
            myPageData.favoriteList,
            myPageData.blackList,
        )
    }

    fun addDiscord(data: String?) {
        val discordList = myPageData.instagramIds?.toMutableList() ?: mutableListOf()
        if(data != null) discordList.add(data)
        myPageData = MyPageDataModel(
            myPageData.name,
            myPageData.phoneNum,
            myPageData.email,
            myPageData.photoId,
            myPageData.instagramIds,
            myPageData.githubIds,
            discordList,
            myPageData.favoriteList,
            myPageData.blackList,
        )
    }

    fun checkNull(): Boolean = myPageData.checkNull()

    fun getData() = myPageData
}