package com.example.cupidscafe.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cupidscafe.Model.CategoryModel
import com.example.cupidscafe.R
import com.example.cupidscafe.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items:MutableList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.Viewholder>() {

    private lateinit var context:android.content.Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1


    inner class Viewholder(val binding: ViewholderCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context=parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.titleCat.text=item.title

        holder.binding.root.setOnClickListener{
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition==position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.orange_bg)
        }else{
            holder.binding.titleCat.setBackgroundResource(R.drawable.edittext_bg)
        }
    }

    override fun getItemCount(): Int = items.size

}
