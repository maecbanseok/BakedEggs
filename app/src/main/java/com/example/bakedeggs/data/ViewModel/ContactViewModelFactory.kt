package com.example.bakedeggs.data.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bakedeggs.data.ContactDataSource
import com.example.bakedeggs.data.ContactRepositoryImpl

class ContactViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)) return ContactViewModel(
            ContactRepositoryImpl(ContactDataSource(application))
        ) as T
        throw IllegalArgumentException()
    }
}