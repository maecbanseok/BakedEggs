package com.example.bakedeggs.mypage.presentation.viewholders

import android.app.Activity
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.databinding.MypageItemCardEmptyBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.presentation.MyPageAddCardFragment
import com.example.bakedeggs.mypage.data.data.MyPageDataObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class CardEmptyViewHolder(val binding: MypageItemCardEmptyBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        activity: Activity,
    ) {
        binding.root.setOnClickListener {
            MyPageAddCardFragment(itemChange!!).show((activity as MainActivity).supportFragmentManager, "Card Add Contact")
        }
    }
}

fun saveData(name: String?, phoneNum: String?, email: String? = null, photoId: Uri? = null) {
    MyPageDataObj.getDataSource().addNewProfile(
        MyPageUIModel.CardModel(
            name = name,
            phoneNum = phoneNum,
            email = email,
            photoId = photoId
        )
    )
}

fun validationAddCard(name: String?, phoneNum: String?): Boolean {
    if (isAddCardEmpty(name, phoneNum)) {
        return false
    }
    return true
}

fun isAddCardEmpty(name: String?, phoneNum: String?): Boolean {
    return name.isNullOrEmpty() || phoneNum.isNullOrEmpty()
}