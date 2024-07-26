package com.example.bakedeggs.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.bakedeggs.main.MainActivity
import com.example.bakedeggs.databinding.MypageItemCardBinding
import com.example.bakedeggs.databinding.MypageItemEmptyBinding
import com.example.bakedeggs.databinding.MypageItemHeaderBinding
import com.example.bakedeggs.databinding.MypageItemListBinding
import com.example.bakedeggs.databinding.MypageItemSnsPlusButtonBinding
import com.example.bakedeggs.databinding.MypageItemTopBarBinding
import com.example.bakedeggs.databinding.MypageItemUneditableListBinding
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import com.example.bakedeggs.mypage.diffutil.MyPageDiffUtilCallback
import com.example.bakedeggs.mypage.viewholders.CardViewHolder
import com.example.bakedeggs.mypage.viewholders.EmptyViewHolder
import com.example.bakedeggs.mypage.viewholders.HeaderViewHolder
import com.example.bakedeggs.mypage.viewholders.ListViewHolder
import com.example.bakedeggs.mypage.viewholders.MyPageViewHolder
import com.example.bakedeggs.mypage.viewholders.SnsPlusButtonViewHolder
import com.example.bakedeggs.mypage.viewholders.TopBarViewHolder
import com.example.bakedeggs.mypage.viewholders.UneditableListViewHolder

enum class ItemViewType(val viewType: Int) {
    TOP_BAR(0), CARD(1), HEADER(2), LIST_EDITABLE(3), LIST_UNEDITABLE(4), SNS_PLUS_BUTTON(5), EMPTY(6)
}

class MyPageRecyclerViewAdapter(private val screenType: Int, private val activity: MainActivity) : ListAdapter<MyPageUIModel, MyPageViewHolder>(
    MyPageDiffUtilCallback()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val binding: ViewBinding?
        lateinit var viewHolder: MyPageViewHolder
        val entry = ItemViewType.entries.find {
            it.viewType == viewType
        } ?: ItemViewType.EMPTY
        when(entry) {
            ItemViewType.TOP_BAR -> {
                binding = MypageItemTopBarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = TopBarViewHolder(binding)
            }
            ItemViewType.CARD -> {
                binding = MypageItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = CardViewHolder(binding)
            }
            ItemViewType.HEADER -> {
                binding = MypageItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = HeaderViewHolder(binding)
            }
            ItemViewType.LIST_EDITABLE -> {
                binding = MypageItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = ListViewHolder(binding)
            }
            ItemViewType.LIST_UNEDITABLE -> {
                binding = MypageItemUneditableListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = UneditableListViewHolder(binding)
            }
            ItemViewType.SNS_PLUS_BUTTON -> {
                binding = MypageItemSnsPlusButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = SnsPlusButtonViewHolder(binding)
            }
            ItemViewType.EMPTY -> {
                binding = MypageItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = EmptyViewHolder(binding)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        when(holder) {
            is TopBarViewHolder -> holder.bind(getItem(position), itemChange)
            is CardViewHolder -> holder.bind(getItem(position), itemChange, activity)
            is HeaderViewHolder -> holder.bind(getItem(position), itemChange)
            is ListViewHolder -> holder.bind(screenType, getItem(position), itemChange, true)
            is UneditableListViewHolder -> holder.bind(getItem(position), itemChange)
            is SnsPlusButtonViewHolder -> holder.bind(getItem(position), itemChange)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        val viewType:Int = when(currentList[position]) {
            is MyPageUIModel.TopBarModel -> ItemViewType.TOP_BAR.ordinal
            is MyPageUIModel.CardModel -> ItemViewType.CARD.ordinal
            is MyPageUIModel.ListModel -> {
                if((currentList[position] as MyPageUIModel.ListModel).isEditable) ItemViewType.LIST_EDITABLE.ordinal
                else ItemViewType.LIST_UNEDITABLE.ordinal
            }
            is MyPageUIModel.HeaderModel -> ItemViewType.HEADER.ordinal
            is MyPageUIModel.SnsPlusButtonModel -> ItemViewType.SNS_PLUS_BUTTON.ordinal
            is MyPageUIModel.EmptyModel -> ItemViewType.EMPTY.ordinal
        }
        return viewType
    }

    interface ItemChange {
        fun onChangeData()
        fun onChangeEditable(isEditable: Boolean)
    }

    var itemChange: ItemChange? = null

}