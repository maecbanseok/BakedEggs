package com.example.bakedeggs.mypage.data

import android.util.Log
import com.example.bakedeggs.data.ViewModel.ContactViewModel
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.data.MyPageDataObj
import kotlinx.coroutines.flow.map

suspend fun ContactViewModel.notNormal(adapter: MyPageRecyclerViewAdapter) {
    contacts.map { contacts -> contacts.filter { it.tag != 0 } }.collect { contacts->
        Log.d("마이페이지",contacts.toString())
        MyPageDataObj.getDataSource().setLike(contacts.filter { it.tag == 1 })
        MyPageDataObj.getDataSource().setBlock(contacts.filter { it.tag == 2 })
        adapter.submitList(listOf())
        adapter.submitList(MyPageDataObj.getDataSource()?.changeUIList())
    }
}