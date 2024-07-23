package com.example.bakedeggs.data

import android.content.Context

class ContactRepositoryImpl(private val contactDataSource: ContactDataSource):ContactRepository {
    override fun getContactList(): ArrayList<ContactEntity> {
        return contactDataSource.ContactEntities
    }

    override fun addContactList(contact: ContactEntity) {
        contactDataSource.ContactEntities.add(contact)
        contactDataSource.ContactEntities.sortBy { it.name }
    }

    override fun modifyContact(idx:Int, contact:ContactEntity) {
        contactDataSource.ContactEntities.set(idx, contact)
    }

    override fun removeContact(idx:Int){
        contactDataSource.ContactEntities.removeAt(idx)
    }

    override fun search(str: String):ArrayList<ContactEntity> {
        val result=ArrayList<ContactEntity>()
        for(i in contactDataSource.ContactEntities){
            if(str.equals(i.name.slice(0..minOf(str.length-1,i.name.length-1)))
                ||str.equals(i.convertedName.slice(0..minOf(str.length-1,i.name.length-1)))){
                result+=i
            }
        }
        return result
    }
}