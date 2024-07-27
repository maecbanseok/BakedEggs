package com.example.bakedeggs.mypage

import android.app.Application
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.mypage.data.model.MyPageDataModel
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import com.example.bakedeggs.mypage.data.changer.extractToMyPageDataObj
import com.example.bakedeggs.mypage.data.model.MyPageSNSListModel

object MyPageDataObj {
    private var myPageData = MyPageDataModel()

    fun initData(application: Application) {
//        val serviceLocator = ServiceLocator(application)
//        serviceLocator.contactRepositoryImpl.getContactList().extractToMyPageDataObj()
        setUIList()
    }

    fun setUIList() {
        myPageData = myPageData.copy(uiModelList = myPageData.makeMyPageUIList())
    }

    fun getUIList(): List<MyPageUIModel> = myPageData.uiModelList ?: listOf()

    fun changeUIList(): List<MyPageUIModel> {
        setUIList()
        return getUIList()
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

    fun addSns(data: MyPageSNSListModel?) {
        val snsList = myPageData.snsIds?.toMutableList() ?: mutableListOf()
        if(data != null) snsList.add(data)
        myPageData = myPageData.copy(snsIds = snsList)
        sortType()
    }

    fun deleteSns(position: Int) {

    }

    fun sortType() {
        myPageData = myPageData.copy(snsIds = myPageData.snsIds?.sortedWith (
            compareBy { it.type }
        ))
    }

    fun checkNull(): Boolean = myPageData.checkNull()

    fun getData() = myPageData
}