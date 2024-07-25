package com.example.bakedeggs.mypage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.MainActivity
import com.example.bakedeggs.databinding.MypageItemCardEmptyBinding
import com.example.bakedeggs.databinding.MypageItemViewpagerBinding
import com.example.bakedeggs.mypage.MyPageDataObj
import com.example.bakedeggs.mypage.data.model.MyPageUIModel
import com.example.bakedeggs.mypage.data.model.MyPageViewPagerUIModel
import com.example.bakedeggs.mypage.diffutil.MyPageViewPagerDiffUtilCallback
import com.example.bakedeggs.mypage.viewholders.CardEmptyViewHolder
import com.example.bakedeggs.mypage.viewholders.ViewPagerViewHolder

const val CARD_EMPTY = 0
const val CARD_NOT_EMPTY = 1

class MyPageViewPagerAdapter(private val uiModel: MyPageUIModel.CardModel,  private val itemChange: MyPageRecyclerViewAdapter.ItemChange?, private val activity: MainActivity) : ListAdapter<MyPageViewPagerUIModel, RecyclerView.ViewHolder>(
    MyPageViewPagerDiffUtilCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        if(viewType == 0) {
            val binding = MypageItemCardEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            viewHolder = CardEmptyViewHolder(binding)
        } else {
            val binding = MypageItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            viewHolder = ViewPagerViewHolder(binding)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewPagerViewHolder) holder.bind(uiModel)
        else if(holder is CardEmptyViewHolder) holder.bind(itemChange, activity)
    }

    override fun getItemCount(): Int = 1

    override fun getItemViewType(position: Int): Int {
        return if(MyPageDataObj.checkNull()) {
            CARD_EMPTY
        } else {
            CARD_NOT_EMPTY
        }
    }

}