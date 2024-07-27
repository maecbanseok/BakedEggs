package com.example.bakedeggs.mypage

import android.util.Log
import com.example.bakedeggs.data.ViewModel.ContactViewModel
import kotlinx.coroutines.flow.map

suspend fun ContactViewModel.notNormal() {
    contacts.map { contacts -> contacts.filter { it.tag != 0 } }.collect {
        MyPageDataObj.getDataSource().setLike(it)
        MyPageDataObj.getDataSource().setBlock(it)
    }
}