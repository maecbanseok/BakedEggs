package com.example.bakedeggs.data

import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    fun getContactList() : Flow<ArrayList<ContactEntity>>
    fun getCallLogs():Flow<ArrayList<CallLogEntity>>
    suspend fun addContact(contactEntity: ContactEntity)
    suspend fun removeContact(contactEntity: ContactEntity)
    suspend fun modifyContact(position: Int,contactEntity: ContactEntity)
    suspend fun fetchData()
}