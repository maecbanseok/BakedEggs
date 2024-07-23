package com.example.bakedeggs.data

interface ContactRepository {
    fun getContactList() : List<ContactEntity>
}