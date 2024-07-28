package com.example.bakedeggs.mypage

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.DialogAddCardBinding
import com.example.bakedeggs.mypage.viewholders.saveData
import com.example.bakedeggs.mypage.viewholders.validationAddCard

class MyPageAddCardFragment(private val itemChange: MyPageRecyclerViewAdapter.ItemChange) : DialogFragment() {
    private var profileUri: Uri? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogBinding = DialogAddCardBinding.inflate(layoutInflater)

        val cropImage = registerForActivityResult(CropImageContract()) { result ->
            println("이거지거 1 $result")
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

        dialogBinding.mypageEtAddCardName.setText(MyPageDataObj.getDataSource().getData().name ?: "")
        dialogBinding.mypageEtAddCardPhone.setText(
            MyPageDataObj.getDataSource().getData().phoneNum ?: ""
        )
        dialogBinding.mypageEtAddCardEmail.setText(MyPageDataObj.getDataSource().getData().email ?: "")
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

        val listener = DialogInterface.OnClickListener { dialogP, which ->
            val dialog = dialogP as AlertDialog
            if (which == DialogInterface.BUTTON_POSITIVE) {
                val name = dialog.findViewById<EditText>(R.id.mypage_et_add_card_name).text.toString()
                val num = dialog.findViewById<EditText>(R.id.mypage_et_add_card_phone).text.toString()
                val email = dialog.findViewById<EditText>(R.id.mypage_et_add_card_email).text.toString()
                //val profile = dialog.findViewById<ImageView>(R.id.mypage_iv_add_card_profile)
//                val profileUri = Uri.parse("android.resource://" + this.requireContext().packageName + "/" + profile)
                //TODO 이미지 추가
                if (validationAddCard(name, num)) {
                    saveData(name, num, email, profileUri)
                    itemChange.onChangeData()
                }
            }
        }
        builder.setPositiveButton("저장", listener)
        builder.setNegativeButton("취소", null)

        return builder.create()
    }
}