package com.example.bakedeggs.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.bakedeggs.mypage.data.MyPageDataModel

class MyPageViewModel: ViewModel() {
    private val _liveData = MutableLiveData<MyPageDataModel?>()
    val liveData: LiveData<MyPageDataModel?> get() = _liveData

    override fun onCleared() {
        super.onCleared()
        _liveData.value = null
    }

    fun initData() = setData()

    fun setData() {
        _liveData.value = MyPageDataObj.getData()
    }

    fun getData(): MyPageDataModel? = liveData.value
}

class MyPageViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return MyPageViewModel() as T
    }
}