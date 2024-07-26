package com.example.bakedeggs.mypage

import android.app.Application
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.mypage.data.model.MyPageDataModel
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import com.example.bakedeggs.mypage.data.changer.extractToMyPageDataObj

object MyPageDataObj {
    private var myPageData = MyPageDataModel()

    fun initData(application: Application) {
        val serviceLocator = ServiceLocator(application)
        serviceLocator.contactRepositoryImpl.getContactList().extractToMyPageDataObj()
    }

    fun addFavorite(entity: ContactEntity) {
        val favoriteList: MutableList<ContactEntity> = myPageData.favoriteList?.toMutableList() ?: mutableListOf<ContactEntity>()
        favoriteList.add(entity)
        myPageData = myPageData.copy(favoriteList = favoriteList)
    }

    fun addBlock(entity: ContactEntity) {
        val blackList: MutableList<ContactEntity> = myPageData.blackList?.toMutableList() ?: mutableListOf<ContactEntity>()
        blackList.add(entity)
        myPageData = myPageData.copy(blackList = blackList)
    }

    fun addNewProfile(data: MyPageUIModel.CardModel) {
        myPageData = myPageData.copy(
            name = data.name,
            phoneNum = data.phoneNum,
            email = data.email,
            photoId = data.photoId,
        )
    }

    fun addInsta(data: String?) {
        val instaList = myPageData.instagramIds?.toMutableList() ?: mutableListOf()
        if(data != null) instaList.add(data)
        myPageData = myPageData.copy(instagramIds = instaList)
    }

    fun addGithub(data: String?) {
        val githubList = myPageData.githubIds?.toMutableList() ?: mutableListOf()
        if(data != null) githubList.add(data)
        myPageData = myPageData.copy(githubIds = githubList)
    }

    fun addDiscord(data: String?) {
        val discordList = myPageData.discordIds?.toMutableList() ?: mutableListOf()
        if(data != null) discordList.add(data)
        myPageData = myPageData.copy(discordIds = discordList)
    }

    fun checkNull(): Boolean = myPageData.checkNull()

    fun getData() = myPageData
}