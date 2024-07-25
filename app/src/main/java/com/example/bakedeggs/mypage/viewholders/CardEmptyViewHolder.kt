package com.example.bakedeggs.mypage.viewholders

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.DialogAddCardBinding
import com.example.bakedeggs.databinding.MypageItemCardEmptyBinding
import com.example.bakedeggs.mypage.MyPageDataObj

class CardEmptyViewHolder(private val binding: MypageItemCardEmptyBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(activity: MainActivity) {
        binding.mypageIvCardAdd.setOnClickListener {
            activity.showAddCardDialog()
        }
    }
}

fun MainActivity.showAddCardDialog() {
    val builder = AlertDialog.Builder(this)
    builder.setView(layoutInflater.inflate(R.layout.dialog_add_card, null))
    val dialogBinding = DialogAddCardBinding.inflate(layoutInflater)
    val etName = dialogBinding.mypageEtAddCardName
    val etNum = dialogBinding.mypageEtAddCardPhone
    etName.setText(MyPageDataObj.getData().name)
    etNum.setText(MyPageDataObj.getData().phoneNum)
    val listener = DialogInterface.OnClickListener { _, which ->
        if(which == DialogInterface.BUTTON_POSITIVE) {
           saveData()
        }
    }
    builder.setPositiveButton("저장", listener)
    builder.setNegativeButton("취소", null)
    builder.show()
}

fun saveData() {
    println("저장")
}