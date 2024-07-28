package com.example.bakedeggs.mypage.data.data

import android.net.Uri
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.mypage.data.model.MyPageDataModel
import com.example.bakedeggs.mypage.data.model.MyPageSNSListModel
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import com.example.bakedeggs.mypage.presentation.makeMyPageUIList

class MyPageData {
    private var myPageData = MyPageDataModel()
    private var mySnsListFirst = 3
    private var myFavoriteListFirst = -1
    private var myBlockListFirst = -1

    init {
        initData()
    }

    fun initData() {
        setUIList()
    }

    fun setSns(n: Int) {
        mySnsListFirst = n
    }

    fun setUIList() {
        myPageData = myPageData.copy(uiModelList = myPageData.makeMyPageUIList())
    }

    fun getUIList(): List<MyPageUIModel> = myPageData.uiModelList ?: listOf()

    fun changeUIList(): List<MyPageUIModel> {
        setUIList()
        setLikeFirst()
        setBlockFirst()
        return getUIList()
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
        if (myPageData.snsIds.isNullOrEmpty()) {
            mySnsListFirst = position
        }
        val snsList = myPageData.snsIds?.toMutableList() ?: mutableListOf()
        if (data != null) snsList.add(data)
        myPageData = myPageData.copy(snsIds = snsList)
        sortType()
    }

    fun deleteSns(position: Int) {
        val list: MutableList<MyPageSNSListModel> = mutableListOf()
        list.addAll(myPageData.snsIds ?: listOf())
        if (list.size - 1 >= position - mySnsListFirst) {
            list.removeAt(position - mySnsListFirst)
            myPageData = myPageData.copy(
                snsIds = list
            )
        }
    }

    fun sortType() {
        myPageData = myPageData.copy(snsIds = myPageData.snsIds?.sortedWith(
            compareBy { it.type }
        ))
    }

    fun setLikeFirst() {
        if (myPageData.favoriteList?.isNotEmpty() == true) {
            myFavoriteListFirst = mySnsListFirst + if (MyPageFlagObj.getFlag().isOpenSNS) {
                (myPageData.snsIds?.size ?: 0)
                + if (myPageData.snsIds?.size == 9) 0 else 1
            } else {0} + 1
        }
    }

    fun setBlockFirst() {
        if (myPageData.blackList?.isNotEmpty() == true) {
            myBlockListFirst = myFavoriteListFirst +
                    if(MyPageFlagObj.getFlag().isOpenFavorite) (myPageData.favoriteList?.size ?: 0)
                    else {0} + 1
        }
    }

    fun setLike(list: List<ContactEntity>) {
        myPageData = myPageData.copy(
            favoriteList = list
        )
        setLikeFirst()
    }

    fun setBlock(list: List<ContactEntity>) {
        myPageData = myPageData.copy(
            blackList = list
        )
        setBlockFirst()
    }

    fun setSnsId(position: Int, id: String) {
        val list: MutableList<MyPageSNSListModel> = mutableListOf()
        list.addAll(myPageData.snsIds ?: listOf())
        if (list.size - 1 >= position - mySnsListFirst) {
            if (myPageData.snsIds != null)
                list.set(
                    position - mySnsListFirst,
                    myPageData.snsIds!!.get(position - mySnsListFirst).copy(snsId = id)
                )
        }
        myPageData = myPageData.copy(snsIds = list)
    }

    fun setPhotoFromPicker(uri: Uri) {
        myPageData = myPageData.copy(
            photoId = uri
        )
    }

    fun checkNull(): Boolean = myPageData.checkNull()

    fun getData() = myPageData

    fun getFirst() = mySnsListFirst

    fun getSnsList() = myPageData.snsIds

    fun getFavoriteFirst() = myFavoriteListFirst

    fun getBlockFirst() = myBlockListFirst

}