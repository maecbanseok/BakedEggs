package com.example.bakedeggs.mypage.viewholders

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.media.Image
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.MainActivity
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.DialogAddCardBinding
import com.example.bakedeggs.databinding.MypageItemCardEmptyBinding
import com.example.bakedeggs.mypage.MyPageDataObj
import com.example.bakedeggs.mypage.adapter.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.MyPageUIModel

class CardEmptyViewHolder(private val binding: MypageItemCardEmptyBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(itemClick: MyPageRecyclerViewAdapter.ItemChange?, activity: MainActivity) {
        binding.mypageIvCardAdd.setOnClickListener {
            activity.showAddCardDialog(itemClick)
        }
    }
}

fun MainActivity.showAddCardDialog(itemClick: MyPageRecyclerViewAdapter.ItemChange?) {
    val builder = AlertDialog.Builder(this)
    builder.setView(layoutInflater.inflate(R.layout.dialog_add_card, null))
    val dialogBinding = DialogAddCardBinding.inflate(layoutInflater)
    val etName = dialogBinding.mypageEtAddCardName
    val etNum = dialogBinding.mypageEtAddCardPhone
    val etEmail = dialogBinding.mypageEtAddCardEmail
    val ivProfile = dialogBinding.mypageIvAddCardProfile
    //etName.setText(MyPageDataObj.getData().name ?: "이름")
    //etNum.setText(MyPageDataObj.getData().phoneNum ?: "전화번호")
    //etEmail.setText(MyPageDataObj.getData().email ?: "이메일(선택)")
    val listener = DialogInterface.OnClickListener { dialogP, which ->
        val dialog = dialogP as AlertDialog
        if(which == DialogInterface.BUTTON_POSITIVE) {
            val name = dialog.findViewById<EditText>(R.id.mypage_et_add_card_name).text.toString()
            val num = dialog.findViewById<EditText>(R.id.mypage_et_add_card_phone).text.toString()
            val email = dialog.findViewById<EditText>(R.id.mypage_et_add_card_email).text.toString()
            //val profile = dialog.findViewById<ImageView>(R.id.mypage_iv_add_card_profile)
            //TODO 이미지 추가
            val profile = R.drawable.mypage_base_photo_summer
            if(validationAddCard(name, num)) {
                println("$name,$num 인데")
                saveData(name, num, email, profile)
                itemClick?.onChangeData()
            }
        }
    }
    builder.setPositiveButton("저장", listener)
    builder.setNegativeButton("취소", null)
    builder.show()
}

fun saveData(name: String?, phoneNum: String?, email: String? = null, photoId: Int? = null) {
    MyPageDataObj.addNewProfile(
        MyPageUIModel.CardModel(
            name = name,
            phoneNum = phoneNum,
            email = email,
            photoId = photoId
        )
    )
}

fun validationAddCard(name: String?, phoneNum: String?): Boolean {
    if (isAddCardEmpty(name, phoneNum)){
        println("널널")
        return false
    }
    return true
}

fun isAddCardEmpty(name: String?, phoneNum: String?):Boolean {
    return name.isNullOrEmpty() || phoneNum.isNullOrEmpty()
}