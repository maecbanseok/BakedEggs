package com.example.bakedeggs.mypage

import android.app.Application
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.mypage.data.MyPageDataModel
import com.example.bakedeggs.mypage.data.extractToMyPageDataObj

object MyPageDataObj {
    private var myPageData = MyPageDataModel()

    fun initData(application: Application) {
//        myPageData = data
        val serviceLocator = ServiceLocator(application)
        serviceLocator.contactRepositoryImpl.getContactList().extractToMyPageDataObj()
    }

    fun addFavorite(entity: ContactEntity) {
        val favoriteList: MutableList<ContactEntity> =  myPageData.favoriteList.toMutableList()
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
        val blackList: MutableList<ContactEntity> = myPageData.blackList.toMutableList()
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

    fun checkNull(): Boolean = myPageData.checkNull()
}