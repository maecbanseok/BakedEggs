package com.example.bakedeggs.mypage

import android.app.Application
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.mypage.data.model.MyPageDataModel
import com.example.bakedeggs.mypage.data.model.MyPageSNSListModel
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class MyPageData {
    private var myPageData = MyPageDataModel()
    private var mySnsListFirst = 0

    init {
        initData()
    }

    fun initData() {
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

    fun addSns(data: MyPageSNSListModel?, position: Int) {
        println(myPageData.snsIds)
        if(myPageData.snsIds.isNullOrEmpty()) {
            mySnsListFirst = position
        }
        val snsList = myPageData.snsIds?.toMutableList() ?: mutableListOf()
        if(data != null) snsList.add(data)
        myPageData = myPageData.copy(snsIds = snsList)
        sortType()
    }

    fun deleteSns(position: Int) {
        val list: MutableList<MyPageSNSListModel> = mutableListOf()
        list.addAll(myPageData.snsIds ?: listOf())
        if(list.size - 1 >= position - mySnsListFirst) {
            list.removeAt(position - mySnsListFirst)
            myPageData = myPageData.copy(
                snsIds = list
            )
        }
        println(myPageData.snsIds)
    }

//    fun setSnsPosition(index: Int, position: Int) {
//        val list: MutableList<MyPageSNSListModel> = mutableListOf()
//        list.addAll(myPageData.snsIds ?: listOf())
//        list[index] = list[index].copy(position = position)
//        myPageData = myPageData.copy(
//            snsIds = list
//        )
//    }

    fun sortType() {
        myPageData = myPageData.copy(snsIds = myPageData.snsIds?.sortedWith (
            compareBy { it.type }
        ))
    }

    fun setLike(list: List<ContactEntity>) {
        myPageData = myPageData.copy(
            favoriteList = list
        )
        println("귀로 ${myPageData.favoriteList}")
    }

    fun setBlock(list: List<ContactEntity>) {
        myPageData = myPageData.copy(
            blackList = list
        )
    }

    fun setSnsId(position: Int, id: String, ) {
        val list: MutableList<MyPageSNSListModel> = mutableListOf()
        list.addAll(myPageData.snsIds ?: listOf())
        if(list.size - 1 >= position - mySnsListFirst) {
            if(myPageData.snsIds != null)
            list.set(position - mySnsListFirst, myPageData.snsIds!!.get(position - mySnsListFirst).copy(snsId = id))
        }
        myPageData = myPageData.copy(snsIds = list)
    }

    fun checkNull(): Boolean = myPageData.checkNull()

    fun getData() = myPageData

    fun getFirst() = mySnsListFirst
}