package com.example.bakedeggs.AddContact

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.red
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.bakedeggs.R
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.databinding.FragmentAddBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import java.util.Calendar

class AddDialogFragment : DialogFragment() {

    private lateinit var contact: ContactEntity
    private lateinit var adapter: MyPageRecyclerViewAdapter
    private val snsList: MutableList<MyPageUIModel> = mutableListOf()

    private val binding by lazy { FragmentAddBinding.inflate(layoutInflater) }
    private lateinit var builder: AlertDialog.Builder

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            builder = AlertDialog.Builder(it)

            fun snsButtonVisibility() {
                adapter.notifyDataSetChanged()// 임시로 해두
                if (binding.addBtnInstagram.isVisible) {
                    binding.addBtnInstagram.visibility = View.INVISIBLE
                    binding.addBtnGithub.visibility = View.INVISIBLE
                    binding.addBtnDiscord.visibility = View.INVISIBLE
                } else {
                    binding.addBtnInstagram.visibility = View.VISIBLE
                    binding.addBtnGithub.visibility = View.VISIBLE
                    binding.addBtnDiscord.visibility = View.VISIBLE
                }
            }

            binding.addIvProfile.setOnClickListener {
                //클릭하면 사진 가져오긔
            }
            binding.addTbtnLike.apply {
                if (isChecked) {
                    background.setTint(R.color.red.red)
                }
            }
            binding.addEtPhone
            binding.addEtPhoneWarning
            binding.addEtEmail
            binding.addEtEmailWarning

            val datePicker: DatePicker = binding.addDpBirthday
            val calendar: Calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            datePicker.init(year, month, day, null) // DatePicker 초기화
            datePicker.maxDate = calendar.timeInMillis // 최대 날짜를 오늘로 설정
            datePicker.updateDate(year,month, day)

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("cycle", "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("cycle", "onViewCreated")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        Log.d("cycle", "onCreateView")
    }

    override fun onPause() {
        super.onPause()
        Log.d("cycle", "onPause")
    }

    override fun onStart() {
        super.onStart()
        Log.d("cycle", "onStart")
    }

}