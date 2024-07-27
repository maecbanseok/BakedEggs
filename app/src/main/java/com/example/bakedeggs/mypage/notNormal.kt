package com.example.bakedeggs.mypage

import com.example.bakedeggs.data.ViewModel.ContactViewModel
import kotlinx.coroutines.flow.map

suspend fun ContactViewModel.notNormal() {
    contacts.map { contacts -> contacts.filter { it.tag == 1 } }.collect {
        MyPageDataObj.getDataSource()?.setLike(it)
    }
    contacts.map { contacts -> contacts.filter { it.tag == 2 } }.collect {
        MyPageDataObj.getDataSource()?.setBlock(it)
    }
}