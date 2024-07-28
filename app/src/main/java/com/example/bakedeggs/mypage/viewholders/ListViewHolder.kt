package com.example.bakedeggs.mypage.viewholders

import android.view.View
import android.view.ViewTreeObserver
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemListBinding
import com.example.bakedeggs.mypage.MyPageData
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class ListViewHolder (private val binding: MypageItemListBinding) : MyPageViewHolder(binding), ListSticker, EditableListAddSticker{
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {}

    override fun bind(
        data: MyPageData?,
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        isEditable: Boolean,
        position: Int,
        count: Int
    ) {
        uiModel as MyPageUIModel.ListModel
        binding.mypageIvListSns.setImageResource(uiModel.iconId ?: R.drawable.mypage_base_photo_summer)
        binding.mypageEtListSns.setText(uiModel.content)
        binding.mypageEtListSns.setOnFocusChangeListener { view: View, focus: Boolean ->
            if(focus) {

            } else {
                data?.setSnsId(position = position, binding.mypageEtListSns.text.toString())
                println("포커스 잃음ㅎ")
                itemChange?.onChangeData()
            }
        }
        binding.mypageIvListDelete.setOnClickListener {
            data?.deleteSns(position = position)
            itemChange?.onChangeDataRange(position, count)
        }
        binding.mypageIvListDelete.setOnFocusChangeListener { view: View, focus: Boolean ->
            if(focus) {
                binding.mypageIvListDelete.performClick()
            }
        }
//        binding.mypageIvListDelete.viewTreeObserver.addOnGlobalFocusChangeListener { b1, b2 ->
//            println("뷰트리 1 $b1 $b2")
//        }
//        ViewTreeObserver.OnWindowFocusChangeListener {
//            data?.setSnsId(position = position, binding.mypageEtListSns.text.toString())
//        }
    }

    override fun bind(
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