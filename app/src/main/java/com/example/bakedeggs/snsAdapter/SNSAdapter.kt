package com.example.bakedeggs.snsAdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.List.ListAdapter
import com.example.bakedeggs.R
import com.example.bakedeggs.data.SNS
import com.example.bakedeggs.databinding.MypageItemListBinding

class SNSAdapter(val snsList:ArrayList<Pair<Int,String>>): RecyclerView.Adapter<SNSAdapter.SNSHolder>() {
    class SNSHolder(binding:MypageItemListBinding):RecyclerView.ViewHolder(binding.root){
        val type = binding.mypageIvListSns
        val id = binding.mypageEtListSns.apply {
            setText("")
        }
        val delete = binding.mypageIvListDelete.apply {
            focusable= View.NOT_FOCUSABLE
        }
    }

    interface onClick{
        fun onClick(position:Int)
    }

    var onClicks:onClick? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SNSHolder {
        return SNSHolder(MypageItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SNSHolder, position: Int) {
        holder.type.setImageResource(when(snsList[position].first){
            0 -> R.drawable.mypage_icon_insta
            1 -> R.drawable.mypage_icon_github
            else -> R.drawable.mypage_icon_discord
        })
        holder.delete.setOnClickListener {
            onClicks?.onClick(position)
            Log.d("삭제",position.toString())
        }
        holder.id.setText(snsList[position].second)
        holder.id.addTextChangedListener {
            snsList[position]=Pair(snsList[position].first,holder.id.text.toString())
        }
    }

    override fun getItemCount(): Int {
        return snsList.size
    }

}