package com.example.bakedeggs.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContactViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactViewModel::class.java)) return ContactViewModel(application) as T
        throw IllegalArgumentException()
    }
}