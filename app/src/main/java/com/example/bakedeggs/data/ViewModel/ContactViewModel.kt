package com.example.bakedeggs.data.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bakedeggs.List.ListAdapter
import com.example.bakedeggs.data.Adapter.CallLogAdapter
import com.example.bakedeggs.data.CallLogEntity
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ContactRepositoryImpl
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
                    Log.d("마이페이지 비교","ㅇ")
        }
    }

    suspend fun getCallLog(callAdapter: CallLogAdapter, num:String){
        callLogs.map{
            callLogs -> callLogs.filter { it.number == num }
        }.collect{
            callAdapter.callLogs = it as ArrayList<CallLogEntity>
            callAdapter.notifyDataSetChanged()
            Log.d("콜로그",it.size.toString())
        }
    }

    fun addContact(contact: ContactEntity) {
        viewModelScope.launch {
            contactRepositoryImpl.addContact(contact)
        }

    }

    fun modifyContact(prev:ContactEntity, contact:ContactEntity) {
        viewModelScope.launch {
            contactRepositoryImpl.modifyContact(prev,contact)
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
