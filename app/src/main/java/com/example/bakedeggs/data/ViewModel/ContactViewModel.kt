package com.example.bakedeggs.data.ViewModel

import android.app.Application
import android.telecom.Call
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakedeggs.data.CallLogEntity
import com.example.bakedeggs.data.ContactDataSource
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ContactRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application) {

    private val contactRepositoryImpl = ContactRepositoryImpl(ContactDataSource(application))
    val contacts=contactRepositoryImpl.getContactList()
    val callLogs=contactRepositoryImpl.getCallLogs()

    init {
        viewModelScope.launch {
            contactRepositoryImpl.fetchData()
        }
    }

    fun notNormalContact(){
        viewModelScope.launch {
            contactRepositoryImpl.notNormal()
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

    fun removeContact(idx:Int){
        viewModelScope.launch {
            contactRepositoryImpl.removeContact(idx)
        }
    }

    fun search(str: String) {
        viewModelScope.launch {
            contactRepositoryImpl.search(str)
        }
    }

    fun fetch(){
        viewModelScope.launch {
            contactRepositoryImpl.fetchData()
        }
    }
}
