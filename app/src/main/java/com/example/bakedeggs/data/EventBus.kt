package com.example.bakedeggs.data

import android.net.Uri
import android.os.Bundle
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventBus {
    private val _events = MutableSharedFlow<Pair<String, Uri?>>(replay = 1)
    val events = _events.asSharedFlow()

    suspend fun produceEvent(event : Pair<String, Uri?>){
        _events.emit(event)
    }
}