package com.example.bakedeggs.List

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.databinding.ListRecyclerviewBinding

class ListAdapter() :
    RecyclerView.Adapter<ListAdapter.ListHolder>() {

    class ListHolder(val binding: ListRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val name = binding.listTvName
        val imgUri = binding.listIvProfile
    }


//    class GridHolder(val binding : GridRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root){
//        val name = binding
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListHolder {
        val binding =
            ListRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.itemView.setOnClickListener{ listClick?.onClick(it, position) }
        holder.itemView.setOnClickListener { listClick?.onPressed(it, position) }

        //if -> 즐겨찾기인지 아닌지 확인하는 구문
    }

   //override fun getItemCount(): Int = listData.size

    interface ListClick{
        fun onClick(view : View, position: Int)
        fun onPressed(view: View, position: Int)
    }
    val listClick : ListClick? = null


}