package com.example.bakedeggs.AddContact

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.InputFilter
import android.util.Log

import android.view.View

import android.widget.DatePicker
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts

import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.core.graphics.toColorInt
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.bakedeggs.R
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.databinding.FragmentAddBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import com.google.android.material.shape.RoundedCornerTreatment
import java.util.Calendar
import java.util.regex.Pattern

class AddDialogFragment : DialogFragment() {

    private lateinit var contact: ContactEntity
    private lateinit var adapter: MyPageRecyclerViewAdapter
    private val snsList: MutableList<MyPageUIModel> = mutableListOf()

    private val binding by lazy { FragmentAddBinding.inflate(layoutInflater) }
    private lateinit var builder: AlertDialog.Builder
    //이미지 자르기
    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // returned uri 사용
            Glide.with(this)
                .load(result.uriContent)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .into(binding.addIvProfile)
        } else {
            // An error occurred.
            val exception = result.error
        }
    }
    //이미지 가져오기
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                cropImage.launch(
                    CropImageContractOptions(
                        uri = uri, // 크롭할 이미지 uri
                        cropImageOptions = CropImageOptions(
                            outputCompressFormat = Bitmap.CompressFormat.PNG
                            // 원하는 옵션 추가
                        )
                    )
                )

            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            builder = AlertDialog.Builder(it)

            fun snsButtonVisibility() {
                adapter.notifyDataSetChanged()// 임시로 해둠
                if (binding.addBtnInstagram.isVisible) {
                    binding.addBtnInstagram.visibility = View.INVISIBLE
                    binding.addBtnGithub.visibility = View.INVISIBLE
                    binding.addBtnDiscord.visibility = View.INVISIBLE
                } else {
                    binding.addBtnInstagram.visibility = View.VISIBLE
                    binding.addBtnGithub.visibility = View.VISIBLE
                    binding.addBtnDiscord.visibility = View.VISIBLE
                }
                binding.addLoScroll.fullScroll(ScrollView.FOCUS_DOWN);
            }

            binding.addIvProfile.setOnClickListener {
                //클릭하면 사진 가져오긔
                // Registers a photo picker activity launcher in single-select mode.
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

            }

            val filterAddEtEmail = InputFilter { source, start, end, dest, dstart, dend ->
                val ps = Pattern.compile("^[ㄱ-ㅣ가-힣a-zA-Z0-9\\@\\.]+$")
                if (!ps.matcher(source).matches()) { "" } else source
            }
            val phonePattern = Regex("^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}\$")
            val emailPattern = Regex("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)\$")


            binding.run {
                addEtPhone.doAfterTextChanged {
                    val phoneNumber = addEtPhone.text.toString().trim()
                    if (phoneNumber.isEmpty()) {
                        addEtPhoneWarning.text = "번호를 입력해 주세요"
                        addEtPhoneWarning.setTextColor(Color.RED)
                    }else {
                        if (!phoneNumber.matches(phonePattern)){
                            addEtPhoneWarning.text = "입력 값을 확인해 주세요"
                            addEtPhoneWarning.setTextColor(Color.RED)
                        }else{
                            addEtPhoneWarning.text = "입력 값을 확인 완료"
                            addEtPhoneWarning.setTextColor(Color.GREEN)
                        }
                    }
                }
                addEtEmail.filters = arrayOf(filterAddEtEmail)
                addEtEmail.doAfterTextChanged {
                    val emailWords = addEtEmail.text.toString().trim()
                    if (emailWords.isEmpty()) {
                        addEtEmailWarning.text = "이메일을 입력해 주세요"
                        addEtEmailWarning.setTextColor(Color.RED)
                    }else {
                        if (!emailWords.matches(emailPattern)){
                            addEtEmailWarning.text = "입력 값을 확인해 주세요"
                            addEtEmailWarning.setTextColor(Color.RED)
                        }else{
                            addEtEmailWarning.text = "입력 값을 확인 완료"
                            addEtEmailWarning.setTextColor(Color.GREEN)
                        }
                    }
                }
            }

            val datePicker: DatePicker = binding.addDpBirthday
            val calendar: Calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            datePicker.init(year, month, day, null) // DatePicker 초기화
            datePicker.maxDate = calendar.timeInMillis // 최대 날짜를 오늘로 설정
            datePicker.updateDate(year, month, day)

            binding.addTbtnFoldbutton.setOnClickListener {
                if (binding.addRvSnsList.isVisible) {
                    binding.addRvSnsList.visibility = View.INVISIBLE
                } else {
                    binding.addRvSnsList.visibility = View.VISIBLE
                }
            }

            adapter = MyPageRecyclerViewAdapter(activity as MainActivity)
            adapter.submitList(listOf())

            binding.addRvSnsList.adapter = adapter

            binding.addBtnSnsadd.setOnClickListener {
                snsButtonVisibility()
                binding.addBtnInstagram.apply {
                    setOnClickListener {
                        snsList.add(
                            MyPageUIModel.ListModel(
                                adapter.itemCount + 1,
                                R.drawable.instagram_24,
                                ""
                            )
                        )
                        adapter.submitList(snsList)
                        snsButtonVisibility()
                    }
                }
                binding.addBtnGithub.apply {
                    setOnClickListener {
                        snsList.add(
                            MyPageUIModel.ListModel(
                                adapter.itemCount + 1,
                                R.drawable.github_24,
                                ""
                            )
                        )
                        adapter.submitList(snsList)
                        snsButtonVisibility()
                    }
                }
                binding.addBtnDiscord.apply {
                    setOnClickListener {
                        snsList.add(
                            MyPageUIModel.ListModel(
                                adapter.itemCount + 1,
                                R.drawable.discord_24,
                                ""
                            )
                        )
                        adapter.submitList(snsList)
                        snsButtonVisibility()
                    }
                }
            }

            binding.addRvSnsList

            val listener = DialogInterface.OnClickListener { _, _ ->
                if (binding.addEtName.text.equals("") ||
                    binding.addEtPhone.text.equals("") ||
                    binding.addEtEmail.text.equals("")
                ) {
                    Toast.makeText(this.requireContext(), "미입력된 항목이 있습니다.", Toast.LENGTH_SHORT)
                } else {
                    contact.apply {
                        name = binding.addEtName.text.toString()
                        convertedName = ""
                        num = binding.addEtPhone.text.toString()
                        email = binding.addEtEmail.text.toString()
                        if (binding.addTbtnLike.isChecked) {
                            tag = 1 // 즐겨찾기
                        } else {
                            tag = 0 // 일반저장
                        }
                        birth = binding.addDpBirthday.toString()
                    }
                }
            }

            builder.setView(binding.root)
                .setPositiveButton("저장", listener)
                .setNegativeButton("취소", null)
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}