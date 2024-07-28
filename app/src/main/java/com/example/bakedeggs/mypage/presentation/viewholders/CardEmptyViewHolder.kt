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

//fun Activity.showAddCardDialog(
//    itemChange: MyPageRecyclerViewAdapter.ItemChange?
//) {
////    val dialogBinding = DialogAddCardBinding.inflate(LayoutInflater.from(this))
//    val dialogBinding = DialogAddCardBinding.inflate(LayoutInflater.from(this))
//    dialogBinding.mypageEtAddCardName.setText(MyPageDataObj.getDataSource().getData().name ?: "")
//    dialogBinding.mypageEtAddCardPhone.setText(
//        MyPageDataObj.getDataSource().getData().phoneNum ?: ""
//    )
//    dialogBinding.mypageEtAddCardEmail.setText(MyPageDataObj.getDataSource().getData().email ?: "")
//    dialogBinding.mypageIvAddCardProfile.setImageURI(
//        MyPageDataObj.getDataSource().getData().photoId
//    )
//    dialogBinding.mypageCvAddCardProfile.setOnClickListener {
//        MyPagePhotoPicker.picker?.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//        println("계란")
//    }
//    val builder = AlertDialog.Builder(this)
//    builder.setView(dialogBinding.root)
//
//    val listener = DialogInterface.OnClickListener { dialogP, which ->
//        val dialog = dialogP as AlertDialog
//        if (which == DialogInterface.BUTTON_POSITIVE) {
//            val name = dialog.findViewById<EditText>(R.id.mypage_et_add_card_name).text.toString()
//            val num = dialog.findViewById<EditText>(R.id.mypage_et_add_card_phone).text.toString()
//            val email = dialog.findViewById<EditText>(R.id.mypage_et_add_card_email).text.toString()
//            //val profile = dialog.findViewById<ImageView>(R.id.mypage_iv_add_card_profile)
//            val profile = R.drawable.mypage_base_photo_summer
//            val profileUri = Uri.parse("android.resource://" + this.packageName + "/" + profile)
//            //TODO 이미지 추가
//            if (validationAddCard(name, num)) {
//                saveData(name, num, email, profileUri)
//                itemChange?.onChangeData()
//            }
//        }
//    }
//    builder.setPositiveButton("저장", listener)
//    builder.setNegativeButton("취소", null)
//    builder.show()
//}

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