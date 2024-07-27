package com.example.bakedeggs.mypage

import android.util.Log
import com.example.bakedeggs.data.ViewModel.ContactViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

suspend fun ContactViewModel.notNormal() {
    contacts.map { contacts -> contacts.filter { it.tag != 0 } }.collect { contacts->
        Log.d("마이페이지",contacts.toString())
        MyPageDataObj.getDataSource().setLike(contacts.filter { it.tag == 1 })
        MyPageDataObj.getDataSource().setBlock(contacts.filter { it.tag == 2 })
    }
}