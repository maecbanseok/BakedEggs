package com.example.bakedeggs.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ContactRepositoryImpl(private val contactDataSource: ContactDataSource):ContactRepository {

    private val _contacts = MutableSharedFlow<ArrayList<ContactEntity>>()
    private val _callLogs = MutableSharedFlow<ArrayList<CallLogEntity>>()
    private val _mypageContact = MutableSharedFlow<ArrayList<ContactEntity>>()
    private val contacts = _contacts.asSharedFlow()
    private val callLogs = _callLogs.asSharedFlow()
    private val mypageContact = _mypageContact.asSharedFlow()

    override fun getContactList(): Flow<ArrayList<ContactEntity>> = contacts

    override fun getCallLogs(): Flow<ArrayList<CallLogEntity>> = callLogs

    override suspend fun notNormal() {
        _mypageContact.emit(contactDataSource.ContactEntities.filter { it.tag!=0 } as ArrayList<ContactEntity>)
    }

    override suspend fun search(str: String) {
        val contacts = contactDataSource.ContactEntities
        val result=ArrayList<ContactEntity>()
        for(i in contacts){
            if(str.equals(i.name.slice(0..minOf(str.length-1,i.name.length-1)))
                ||str.equals(i.convertedName.slice(0..minOf(str.length-1,i.name.length-1)))){
                result+=i
            }
        }
        _contacts.emit(result)
    }

    override suspend fun addContact(contactEntity: ContactEntity) {
        contactDataSource.ContactEntities.add(contactEntity)
        contactDataSource.ContactEntities.sortBy { it.name }
        fetchData()
    }
    override suspend fun removeContact(position:Int) {
        contactDataSource.ContactEntities.removeAt(position)
        fetchData()
    }

    override suspend fun modifyContact(position: Int, contactEntity: ContactEntity) {
        contactDataSource.ContactEntities[position] = contactEntity
        fetchData()
    }

    override suspend fun fetchData() {
        _contacts.emit(contactDataSource.ContactEntities)
        _callLogs.emit(contactDataSource.CallLogEntities)
    }



}