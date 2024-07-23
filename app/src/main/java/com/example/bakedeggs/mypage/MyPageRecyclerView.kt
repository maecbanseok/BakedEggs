package com.example.bakedeggs.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.bakedeggs.databinding.MypageItemCardBinding
import com.example.bakedeggs.databinding.MypageItemEmptyBinding
import com.example.bakedeggs.databinding.MypageItemHeaderBinding
import com.example.bakedeggs.databinding.MypageItemListBinding
import com.example.bakedeggs.databinding.MypageItemSnsPlusButtonBinding
import com.example.bakedeggs.databinding.MypageItemTopBarBinding
import com.example.bakedeggs.mypage.viewholders.CardViewHolder
import com.example.bakedeggs.mypage.viewholders.EmptyViewHolder
import com.example.bakedeggs.mypage.viewholders.HeaderViewHolder
import com.example.bakedeggs.mypage.viewholders.ListViewHolder
import com.example.bakedeggs.mypage.viewholders.MyPageViewHolder
import com.example.bakedeggs.mypage.viewholders.SnsPlusButtonViewHolder
import com.example.bakedeggs.mypage.viewholders.TopBarViewHolder

enum class ItemViewType {
    TOP_BAR, CARD, HEADER, LIST, SNS_PLUS_BUTTON, EMPTY
}

class MyPageRecyclerView : ListAdapter<MyPageUIModel, MyPageViewHolder>(
    MyPageDiffUtilCallback()
) {

    val list: List<MyPageUIModel> = currentList.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val binding: ViewBinding?
        lateinit var viewHolder: MyPageViewHolder
        val entry = ItemViewType.entries.find {
            it.ordinal == viewType
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
            ItemViewType.LIST -> {
                binding = MypageItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = ListViewHolder(binding)
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
            is TopBarViewHolder -> holder.bind()
            is CardViewHolder -> holder.bind()
            is HeaderViewHolder -> holder.bind()
            is ListViewHolder -> holder.bind()
            is SnsPlusButtonViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val viewType:Int = when(list[position]) {
            is MyPageUIModel.TopBarModel -> ItemViewType.TOP_BAR.ordinal
            is MyPageUIModel.CardModel -> ItemViewType.CARD.ordinal
            is MyPageUIModel.ListModel -> ItemViewType.LIST.ordinal
            is MyPageUIModel.HeaderModel -> ItemViewType.HEADER.ordinal
            is MyPageUIModel.SnsPlusButtonModel -> ItemViewType.SNS_PLUS_BUTTON.ordinal
            is MyPageUIModel.EmptyModel -> ItemViewType.EMPTY.ordinal
        }
        return viewType
    }

//    val snsAccountList: List<ListElement> = listOf()
//    val favoriteList: List<ListElement> = listOf()
//    val blackList: List<ListElement> = listOf()

//    fun getSnsListRange(): IntRange {
//        val start = 3
//        return getListRange(start, snsAccountList)
//    }
//
//    fun getFavoriteListRange(): IntRange {
//        val start = getSnsListRange().last + 2
//        return getListRange(start, favoriteList)
//    }
//
//    fun getBlackListRange(): IntRange {
//        val start = getFavoriteListRange().last + 2
//        return getListRange(start, blackList)
//    }
//
//    fun getListRange(start: Int, list: List<ListElement>): IntRange = (start..<list.size + start)

}