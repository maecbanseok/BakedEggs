package com.example.bakedeggs.data

interface ContactRepository {
    fun getContactList() : ArrayList<ContactEntity>
    fun addContactList(contact: ContactEntity)
    fun modifyContact(idx:Int, contact:ContactEntity)
    fun removeContact(idx:Int)
    fun search(str:String) :ArrayList<ContactEntity>
}