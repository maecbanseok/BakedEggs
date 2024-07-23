package com.example.bakedeggs.data

import android.os.Bundle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventBus {
    private val _events = MutableSharedFlow<Boolean>()
    val events = _events.asSharedFlow()

    suspend fun produceEvent(event : Boolean){
        _events.emit(event)
    }
}