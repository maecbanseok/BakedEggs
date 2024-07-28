package com.example.bakedeggs.mypage

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.databinding.MypageItemBlockListBinding
import com.example.bakedeggs.databinding.MypageItemCardBinding
import com.example.bakedeggs.databinding.MypageItemEmptyBinding
import com.example.bakedeggs.databinding.MypageItemFavoriteListBinding
import com.example.bakedeggs.databinding.MypageItemHeaderBinding
import com.example.bakedeggs.databinding.MypageItemListBinding
import com.example.bakedeggs.databinding.MypageItemSnsPlusButtonBinding
import com.example.bakedeggs.databinding.MypageItemTopBarBinding
import com.example.bakedeggs.mypage.data.data.MyPageData
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import com.example.bakedeggs.mypage.data.diffutil.MyPageDiffUtilCallback
import com.example.bakedeggs.mypage.presentation.viewholders.BlockListViewHolder
import com.example.bakedeggs.mypage.presentation.viewholders.CardViewHolder
import com.example.bakedeggs.mypage.presentation.viewholders.EmptyViewHolder
import com.example.bakedeggs.mypage.presentation.viewholders.FavoriteListViewHolder
import com.example.bakedeggs.mypage.presentation.viewholders.HeaderViewHolder
import com.example.bakedeggs.mypage.presentation.viewholders.ListViewHolder
import com.example.bakedeggs.mypage.presentation.viewholders.MyPageViewHolder
import com.example.bakedeggs.mypage.presentation.viewholders.SnsPlusButtonViewHolder
import com.example.bakedeggs.mypage.presentation.viewholders.TopBarViewHolder

enum class ItemViewType(val viewType: Int) {
    EMPTY(-1),
    TOP_BAR(0),
    CARD(1),
    HEADER(2),
    LIST_EDITABLE(3),
    SNS_PLUS_BUTTON(4),
    LIST_FAVORITE(5),
    LIST_BLOCK(6),
}

class MyPageRecyclerViewAdapter(private val data: MyPageData?, private val activity: Activity) : ListAdapter<MyPageUIModel, MyPageViewHolder>(
    MyPageDiffUtilCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val binding: ViewBinding?
        lateinit var viewHolder: MyPageViewHolder
        val entry = ItemViewType.entries.find {
            it.viewType == viewType
        } ?: ItemViewType.EMPTY
        when(entry) {
            ItemViewType.EMPTY -> {
                binding = MypageItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = EmptyViewHolder(binding)
            }
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
            ItemViewType.SNS_PLUS_BUTTON -> {
                binding = MypageItemSnsPlusButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = SnsPlusButtonViewHolder(binding)
            }
            ItemViewType.LIST_FAVORITE -> {
                binding = MypageItemFavoriteListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = FavoriteListViewHolder(binding)
            }
            ItemViewType.LIST_BLOCK -> {
                binding = MypageItemBlockListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = BlockListViewHolder(binding)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        when(holder) {
            is TopBarViewHolder -> holder.bind(getItem(position), itemChange, activity)
            is CardViewHolder -> holder.bind(getItem(position), itemChange, activity)
            is HeaderViewHolder -> holder.bind(getItem(position), itemChange)
            is ListViewHolder -> holder.bind(data, getItem(position), itemChange, activity)
            is SnsPlusButtonViewHolder -> holder.bind(getItem(position), itemChange, position, itemCount)
            is FavoriteListViewHolder -> holder.bind(getItem(position), itemChange, position)
            is BlockListViewHolder -> holder.bind(getItem(position), itemChange, position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        val viewType:Int = when(currentList[position]) {
            is MyPageUIModel.TopBarModel -> ItemViewType.TOP_BAR.viewType
            is MyPageUIModel.CardModel -> ItemViewType.CARD.viewType
            is MyPageUIModel.ListModel -> ItemViewType.LIST_EDITABLE.viewType
            is MyPageUIModel.HeaderModel -> ItemViewType.HEADER.viewType
            is MyPageUIModel.SnsPlusButtonModel -> ItemViewType.SNS_PLUS_BUTTON.viewType
            is MyPageUIModel.EmptyModel -> ItemViewType.EMPTY.viewType
            is MyPageUIModel.FavoriteListModel -> ItemViewType.LIST_FAVORITE.viewType
            is MyPageUIModel.BlockListModel -> ItemViewType.LIST_BLOCK.viewType
        }
        return viewType
    }

    interface ItemChange {
        fun onChangeData()
        fun onChangeTag(entity: ContactEntity)
    }

    var itemChange: ItemChange? = null

}