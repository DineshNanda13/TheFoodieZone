package com.example.thefoodiezone.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.thefoodiezone.data.model.Category
import com.example.thefoodiezone.databinding.FoodCatagoryItemViewBinding

class FoodCategoryAdapter: RecyclerView.Adapter<FoodCategoryAdapter.FoodCategoryViewHolder>() {

    private val callBack = object : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.alias == newItem.alias && oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
        val binding = FoodCatagoryItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodCategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category)
    }

    inner class FoodCategoryViewHolder(val binding: FoodCatagoryItemViewBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(category: Category){
            binding.tvFoodCategoryName.text = category.title
        }

    }
}