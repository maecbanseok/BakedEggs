package com.example.bakedeggs.data

import android.content.Context

class ContactRepositoryImpl(private val contactDataSource: ContactDataSource):ContactRepository {
    override fun getContactList(): List<ContactEntity> {
        return contactDataSource.ContactEntities
    }
}