package com.example.bakedeggs.AddContact

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.red
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.transition.Visibility
import com.example.bakedeggs.R
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.databinding.FragmentAddBinding
import com.example.bakedeggs.mypage.MyPageRecyclerView
import com.example.bakedeggs.mypage.MyPageUIModel
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

class AddDialogFragment : DialogFragment() {

    private lateinit var contact : ContactEntity
    private lateinit var adapter : MyPageRecyclerView
    private val snsList : MutableList<MyPageUIModel> = mutableListOf()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val binding = FragmentAddBinding.inflate(layoutInflater)
            fun snsButtonVisibility(){
                if(binding.addBtnInstagram.isVisible){
                    binding.addBtnInstagram.visibility = View.INVISIBLE
                    binding.addBtnGithub.visibility = View.INVISIBLE
                    binding.addBtnDiscord.visibility = View.INVISIBLE
                }else{
                    binding.addBtnInstagram.visibility = View.VISIBLE
                    binding.addBtnGithub.visibility = View.VISIBLE
                    binding.addBtnDiscord.visibility = View.VISIBLE
                }
            }

            binding.addIvProfile.setOnClickListener {
                //클릭하면 사진 가져오긔
            }
            binding.addTbtnLike.apply {
                if (isChecked){
                    background.setTint(R.color.red.red)
                }
            }
            binding.addEtPhone
            binding.addEtPhoneWarning
            binding.addEtEmail
            binding.addEtEmailWarning

            binding.addDpBirthday.maxDate

            binding.addTbtnFoldbutton.setOnClickListener {
                if (binding.addRvSnsList.isVisible){
                    binding.addRvSnsList.visibility = View.INVISIBLE
                }else{
                    binding.addRvSnsList.visibility = View.VISIBLE
                }
            }
            adapter = MyPageRecyclerView()
            adapter.submitList(listOf())
            binding.addRvSnsList.adapter = adapter

            binding.addBtnSnsadd.setOnClickListener {
                snsButtonVisibility()
                binding.addBtnInstagram.apply {
                    setOnClickListener {
                        snsList.add(MyPageUIModel.ListModel(adapter.itemCount+1 ,R.drawable.instagram_24,""))
                        adapter.submitList(snsList)
                        snsButtonVisibility()
                    }
                }
                binding.addBtnGithub.apply {
                    setOnClickListener {
                        snsList.add(MyPageUIModel.ListModel(adapter.itemCount+1 ,R.drawable.github_24,""))
                        adapter.submitList(snsList)
                        snsButtonVisibility()
                    }
                }
                binding.addBtnDiscord.apply {
                    setOnClickListener {
                        snsList.add(MyPageUIModel.ListModel(adapter.itemCount+1 ,R.drawable.discord_24,""))
                        adapter.submitList(snsList)
                        snsButtonVisibility()
                    }
                }
            }

            binding.addRvSnsList

            val listener = DialogInterface.OnClickListener { _,_ ->
                if (binding.addEtName.text.equals("") ||
                    binding.addEtPhone.text.equals("") ||
                    binding.addEtEmail.text.equals("") ){
                    Toast.makeText(this.requireContext(),"미입력된 항목이 있습니다.", Toast.LENGTH_SHORT)
                }else{
                    contact.name = binding.addEtName.text.toString()
                    contact.convertedName = "뭐지?"
                    contact.num = binding.addEtPhone.text.toString()
                    contact.email = binding.addEtEmail.text.toString()
                    if (binding.addTbtnLike.isChecked){
                        contact.tag = 1 // 즐겨찾기
                    }else{
                        contact.tag = 0 // 일반저장
                    }
                    contact.birth = binding.addDpBirthday.toString()
                }
            }

            builder.setView(binding.root)
                .setPositiveButton("저장",listener)
                .setNegativeButton("취소",null)
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}