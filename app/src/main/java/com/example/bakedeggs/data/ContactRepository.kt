package com.example.bakedeggs.data

import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getContactList() : Flow<ArrayList<ContactEntity>>
    fun getCallLogs():Flow<ArrayList<CallLogEntity>>
    suspend fun notNormal()
    suspend fun search(str:String)
    suspend fun addContact(contactEntity: ContactEntity)
    suspend fun removeContact(position: Int)
    suspend fun modifyContact(position: Int,contactEntity: ContactEntity)
    suspend fun fetchData()
}