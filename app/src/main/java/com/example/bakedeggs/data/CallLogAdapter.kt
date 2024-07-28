package com.example.bakedeggs.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bakedeggs.databinding.DetailItemCallLogBinding

class CallLogAdapter(var callLogs : ArrayList<CallLogEntity>): RecyclerView.Adapter<CallLogAdapter.CallHolder>() {

    class CallHolder(val binding: DetailItemCallLogBinding): RecyclerView.ViewHolder(binding.root){
        val type = binding.callTvType
        val date = binding.callTvDate
        val duration = binding.callTvDuration
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallHolder {
        return CallHolder(DetailItemCallLogBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CallHolder, position: Int) {
        holder.type.text = callLogs[position].type
        holder.date.text = callLogs[position].date
        holder.duration.text = callLogs[position].Duration.toInt().toString()+" 초"
    }

    override fun getItemCount(): Int {
        return callLogs.size
    }
}