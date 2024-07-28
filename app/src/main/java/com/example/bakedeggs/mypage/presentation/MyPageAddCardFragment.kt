package com.example.bakedeggs.mypage.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.bakedeggs.R
import com.example.bakedeggs.data.EventBus
import com.example.bakedeggs.databinding.DialogAddCardBinding
import com.example.bakedeggs.mypage.data.data.MyPageDataObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.data.MyPageData
import com.example.bakedeggs.mypage.presentation.viewholders.saveData
import com.example.bakedeggs.mypage.presentation.viewholders.validationAddCard
import com.example.bakedeggs.mypage.verifyEmail
import com.example.bakedeggs.mypage.verifyPhoneNumber
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MyPageAddCardFragment(private val itemChange: MyPageRecyclerViewAdapter.ItemChange) :
    DialogFragment() {
    private var profileUri: Uri? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBinding = DialogAddCardBinding.inflate(layoutInflater)

        val cropImage = registerForActivityResult(CropImageContract()) { result ->
            if (result.isSuccessful) {
                // returned uri 사용
                Glide.with(this)
                    .load(result.uriContent)
                    .into(dialogBinding.mypageIvAddCardProfile)
                profileUri = result.uriContent
                MyPageDataObj.getDataSource().setPhotoFromPicker(profileUri!!)
            } else {
                val exception = result.error
            }
        }

        val pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: $uri")
                    cropImage.launch(
                        CropImageContractOptions(
                            uri = uri, // 크롭할 이미지 uri
                            cropImageOptions = CropImageOptions(
                                outputCompressFormat = Bitmap.CompressFormat.PNG,//사진 확장자 변경
                                minCropResultHeight = 50,//사진 최소 세로크기
                                minCropResultWidth = 80,//사진 최소 가로크기
                                aspectRatioY = 5,//세로 비율
                                aspectRatioX = 8,//가로 비율
                                fixAspectRatio = true,//커터? 크기 고정 여부
                                borderLineColor = Color.GREEN//커터? 태두리 색
                                // 원하는 옵션 추가
                            )
                        )
                    )
                } else {
                    Log.d("PhotoPicker", "No media selected")
                }
            }

        dialogBinding.mypageEtAddCardName.setText(
            MyPageDataObj.getDataSource().getData().name ?: ""
        )
        dialogBinding.mypageEtAddCardPhone.setText(
            MyPageDataObj.getDataSource().getData().phoneNum ?: ""
        )
        dialogBinding.mypageEtAddCardEmail.setText(
            MyPageDataObj.getDataSource().getData().email ?: ""
        )
        dialogBinding.mypageIvAddCardProfile.setImageURI(
            MyPageDataObj.getDataSource().getData().photoId
        )
//        dialogBinding.mypageCvAddCardProfile.setOnFocusChangeListener { _, focus ->
//            if(focus) dialogBinding.mypageCvAddCardProfile.performClick()
//        }
        dialogBinding.mypageCvAddCardProfile.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogBinding.root)

        dialogBinding.mypageAddCardTvSave.setOnClickListener {
            val name = dialogBinding.mypageEtAddCardName.text.toString()
            val num = dialogBinding.mypageEtAddCardPhone.text.toString()
            val email = dialogBinding.mypageEtAddCardEmail.text.toString()
            if (profileUri == null) profileUri = (MyPageDataObj.getDataSource().getData().photoId
                ?: Uri.parse("android.resource://" + this@MyPageAddCardFragment.requireContext().packageName + "/" + R.drawable.mypage_base_photo_summer))


            if (name.isNotEmpty() && num.isNotEmpty()) {
                if (!num.verifyPhoneNumber()) {
                    Toast.makeText(requireActivity(), "전화번호 형식이 잘못됐습니다.", Toast.LENGTH_SHORT).show()
                } else if (!email.verifyEmail()) {
                    Toast.makeText(requireActivity(), "이메일 형식이 잘못됐습니다..", Toast.LENGTH_SHORT).show()
                } else {
                    saveData(name, num, email, profileUri)
                    lifecycleScope.launch {
                        EventBus.produceEvent(Pair(name, profileUri))
                    }
                    itemChange.onChangeData()
                    this.dismiss()
                    Toast.makeText(requireActivity(), "환영합니다 ${name}님.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireActivity(), "이름 또는 전화번호에 빈 값이 입력됐습니다.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        dialogBinding.mypageAddCardTvCancel.setOnClickListener {
            this.dismiss()
        }


//        val listener = DialogInterface.OnClickListener { dialogP, which ->
//            val dialog = dialogP as AlertDialog
//            if (which == DialogInterface.BUTTON_POSITIVE) {
//                val name =
//                    dialog.findViewById<EditText>(R.id.mypage_et_add_card_name).text.toString()
//                val num =
//                    dialog.findViewById<EditText>(R.id.mypage_et_add_card_phone).text.toString()
//                val email =
//                    dialog.findViewById<EditText>(R.id.mypage_et_add_card_email).text.toString()
//                //val profile = dialog.findViewById<ImageView>(R.id.mypage_iv_add_card_profile)
//
//            }
//        }

        return builder.create()
    }
}