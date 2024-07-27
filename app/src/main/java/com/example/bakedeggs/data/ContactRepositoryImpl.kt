package com.example.bakedeggs.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filter

class ContactRepositoryImpl(private val contactDataSource: ContactDataSource):ContactRepository {

    private val _contacts = MutableSharedFlow<ArrayList<ContactEntity>>(replay = 1)
    private val _callLogs = MutableSharedFlow<ArrayList<CallLogEntity>>()
    private val contacts = _contacts.asSharedFlow()
    private val callLogs = _callLogs.asSharedFlow()

    override fun getContactList(): Flow<ArrayList<ContactEntity>> = contacts

    override fun getCallLogs(): Flow<ArrayList<CallLogEntity>> = callLogs

    override suspend fun addContact(contactEntity: ContactEntity) {
        contactDataSource.ContactEntities.add(contactEntity)
        contactDataSource.ContactEntities.sortWith(compareBy<ContactEntity> {-it.tag}.thenBy { it.name })
        _contacts.emit(contactDataSource.ContactEntities)
    }
    override suspend fun removeContact(contactEntity: ContactEntity) {
        contactDataSource.ContactEntities.remove(contactEntity)
        _contacts.emit(contactDataSource.ContactEntities)
    }

    override suspend fun modifyContact(prev: ContactEntity, contactEntity: ContactEntity) {
        contactDataSource.ContactEntities[contactDataSource.ContactEntities.indexOf(prev)] = contactEntity
        contactDataSource.ContactEntities.sortWith(compareBy<ContactEntity> {-it.tag}.thenBy { it.name })
        _contacts.emit(contactDataSource.ContactEntities)
    }

    override suspend fun fetchData() {
        _contacts.emit(contactDataSource.ContactEntities)
        _callLogs.emit(contactDataSource.CallLogEntities)
    }



}