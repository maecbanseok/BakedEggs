package com.example.bakedeggs.mypage.viewholders

import androidx.core.widget.doAfterTextChanged
import com.example.bakedeggs.R
import com.example.bakedeggs.databinding.MypageItemListBinding
import com.example.bakedeggs.mypage.MyPageDataObj
import com.example.bakedeggs.mypage.MyPageRecyclerViewAdapter
import com.example.bakedeggs.mypage.SNSListTypeEnum
import com.example.bakedeggs.mypage.data.model.MyPageUIModel

class ListViewHolder (private val binding: MypageItemListBinding) : MyPageViewHolder(binding), ListSticker, EditableListAddSticker{
    override fun bind(uiModel: MyPageUIModel, itemChange: MyPageRecyclerViewAdapter.ItemChange?) {
        uiModel as MyPageUIModel.ListModel
        binding.mypageIvListSns.setImageResource(uiModel.iconId ?: R.drawable.mypage_base_photo_summer)
        binding.mypageEtListSns.setText(uiModel.content)
        binding.mypageIvListSave.setOnClickListener {

        }
    }

    override fun bind(
        screenType: Int,
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        isEditable: Boolean
    ) {
        uiModel as MyPageUIModel.ListModel
        val screen = SNSListTypeEnum.entries.find {
            it.screenType == screenType
        }
        when(screen) {
            SNSListTypeEnum.MY_PAGE -> {
                binding.mypageIvListSns.setImageResource(uiModel.iconId ?: R.drawable.mypage_base_photo_summer)
                binding.mypageEtListSns.setText(uiModel.content)
                binding.mypageIvListSave.setOnClickListener {

                }
            }
            SNSListTypeEnum.ADD_CONTACT -> {

            }
            null -> TODO()
        }
    }

    override fun bind(
        uiModel: MyPageUIModel,
        itemChange: MyPageRecyclerViewAdapter.ItemChange?,
        index: Int
    ) {

    }
}