package com.example.bakedeggs.mypage.presentation.viewholders

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemListBinding
import com.example.bakedeggs.mypage.data.data.MyPageData
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class ListViewHolder (private val binding: MypageItemListBinding) : MyPageViewHolder(binding) {
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {}

    fun bind(
        data: MyPageData?,
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        activity: Activity
    ) {
        uiModel as MyPageUIModel.ListModel
        binding.mypageIvListSns.setImageResource(uiModel.iconId ?: R.drawable.mypage_base_photo_summer)
        binding.mypageEtListSns.setText(uiModel.content)
        binding.mypageEtListSns.setOnFocusChangeListener { view: View, focus: Boolean ->
            if(focus) {

            } else {
                data?.setSnsId(position = position, binding.mypageEtListSns.text.toString())
                itemChange?.onChangeData()
            }
        }
        binding.mypageEtListSns.setOnEditorActionListener { tv, action, event ->
            var handled = false
            if(action == EditorInfo.IME_ACTION_DONE) {
                val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.mypageEtListSns.windowToken, 0)
                binding.mypageEtListSns.clearFocus()
                handled = true
            }

            handled
        }
        binding.mypageIvListDelete.setOnClickListener {
            data?.deleteSns(position = position)
            itemChange?.onChangeData()
        }
//        binding.mypageIvListDelete.setOnFocusChangeListener { view: View, focus: Boolean ->
//            if(focus) {
//                binding.mypageIvListDelete.performClick()
//            }
//        }
//        binding.mypageIvListDelete.viewTreeObserver.addOnGlobalFocusChangeListener { b1, b2 ->
//            println("뷰트리 1 $b1 $b2")
//        }
//        ViewTreeObserver.OnWindowFocusChangeListener {
//            data?.setSnsId(position = position, binding.mypageEtListSns.text.toString())
//        }
    }

    fun bind(
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        index: Int
    ) {

    }

    var idx=0

    companion object{
        val list=ArrayList<String>()
    }
}