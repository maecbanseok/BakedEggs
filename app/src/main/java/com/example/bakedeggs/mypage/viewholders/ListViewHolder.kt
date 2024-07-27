package com.example.bakedeggs.mypage.viewholders

import android.view.View
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
        position: Int
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
        binding.mypageIvListDelete.setOnClickListener {
            data?.deleteSns(position = position)
            itemChange?.onChangeData()
        }
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