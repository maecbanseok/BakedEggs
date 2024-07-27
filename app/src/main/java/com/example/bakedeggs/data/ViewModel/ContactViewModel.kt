package com.example.bakedeggs.data.ViewModel

import android.app.Application
import android.telecom.Call
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakedeggs.List.ListAdapter
import com.example.bakedeggs.data.CallLogEntity
import com.example.bakedeggs.data.ContactDataSource
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ContactRepository
import com.example.bakedeggs.data.ContactRepositoryImpl
import com.example.bakedeggs.mypage.MyPageDataObj
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ContactViewModel(private val contactRepositoryImpl: ContactRepositoryImpl): ViewModel() {

    val contacts=contactRepositoryImpl.getContactList()
    val callLogs=contactRepositoryImpl.getCallLogs()

    var str=""

    init {
        viewModelScope.launch {
            contactRepositoryImpl.fetchData()
        }
    }

    suspend fun search(listAdapter: ListAdapter){
        contacts.map { contacts -> contacts.filter { it.tag!=2&&(str.equals(it.name.slice(0..minOf(str.length-1,it.name.length-1)))
                || str.equals((it.convertedName.slice(0..minOf(str.length-1,it.convertedName.length-1))))) } }.collect{
                    listAdapter.getData=it as ArrayList<ContactEntity>
                    listAdapter.notifyDataSetChanged()
        }
    }

    fun addContact(contact: ContactEntity) {
        viewModelScope.launch {
            contactRepositoryImpl.addContact(contact)
        }

    }

    fun modifyContact(idx:Int, contact:ContactEntity) {
        viewModelScope.launch {
            contactRepositoryImpl.modifyContact(idx,contact)
        }
    }

    fun removeContact(contact: ContactEntity){
        viewModelScope.launch {
            contactRepositoryImpl.removeContact(contact)
        }
    }

    fun fetch(){
        viewModelScope.launch {
            contactRepositoryImpl.fetchData()
        }
    }
}
