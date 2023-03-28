package com.sultonbek1547.realtimedbfirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sultonbek1547.realtimedbfirebase.databinding.RvItemBinding

class RvAdapter(val list: ArrayList<MyMessage> = ArrayList()) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(val itemRvBinding: RvItemBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(myMessage: MyMessage, position: Int) {
            itemRvBinding.tvDate.text = myMessage.date
            itemRvBinding.tvText.text = myMessage.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) =
        holder.onBind(list[position], position)


    override fun getItemCount(): Int = list.size


}