package com.example.bakedeggs.ViewModel

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
    private val _contacts=contactRepositoryImpl.getContactList()
    private val _callLogs=contactRepositoryImpl.getCallLogs()

    init {
        viewModelScope.launch {
            contactRepositoryImpl.fetchData()
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
}