package com.example.bakedeggs.List

import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.data.ContactEntity
import com.example.bakedeggs.data.ContactRepositoryImpl
import com.example.bakedeggs.data.ServiceLocator
import com.example.bakedeggs.databinding.ListRecyclerviewBinding

class ListAdapter(var getData : ArrayList<ContactEntity>) :
    RecyclerView.Adapter<ListAdapter.ListHolder>() {

    class ListHolder(val binding: ListRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.listTvName
        val img = binding.listIvProfile
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListHolder {
        val binding =
            ListRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.itemView.setOnClickListener { listClick?.onClick(it, position) }
        holder.itemView.setOnLongClickListener { listClick?.onLongClick(it, position)
            true }

        holder.name.text = getData[position].name
        //if -> 즐겨찾기인지 아닌지 확인하는 구문


        val img = getData[position].img?.let {
            //uri 있는지 확인
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(
                    ImageDecoder.createSource(
                        holder.binding.root.context.contentResolver,
                        it
                    )
                )
            } else {
                MediaStore.Images.Media.getBitmap(holder.binding.root.context.contentResolver, it)
            }
        }
        holder.img.setImageBitmap(img)
    }

    override fun getItemCount(): Int {
        return getData.size
    }

    interface ListClick {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View, position: Int)
    }

    var listClick: ListClick? = null


}