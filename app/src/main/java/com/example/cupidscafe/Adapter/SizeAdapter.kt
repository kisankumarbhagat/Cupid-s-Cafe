package com.example.cupidscafe.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cupidscafe.R
import com.example.cupidscafe.databinding.ViewholderOfferBinding
import com.example.cupidscafe.databinding.ViewholderSizeBinding
import com.google.firebase.database.core.Context

class SizeAdapter(val items: MutableList<String>):RecyclerView.Adapter<SizeAdapter.Viewholder>() {
    private var selectedPosition=-1
    private var lastSelectedPosition=-1
    private lateinit  var context:android.content.Context

    inner class Viewholder(val binding: ViewholderSizeBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SizeAdapter.Viewholder, position: Int) {
        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition == position){
            holder.binding.img.setBackgroundResource(R.drawable.orange_bg)
        }else{ holder.binding.img.setBackgroundResource(R.drawable.size_bg)

        }
        val imageSize = when(position){
            0->45.dpToPx(context)
            1->50.dpToPx(context)
            2->55.dpToPx(context)
            3->65.dpToPx(context)
            else->70.dpToPx(context)
        }
        val layoutParams = holder.binding.img.layoutParams
        layoutParams.width=imageSize
        layoutParams.height=imageSize
        holder.binding.img.layoutParams=layoutParams
    }

    private fun Int.dpToPx(context: android.content.Context):Int{
        return (this*context.resources.displayMetrics.density).toInt()
    }
    override fun getItemCount(): Int = items.size
}