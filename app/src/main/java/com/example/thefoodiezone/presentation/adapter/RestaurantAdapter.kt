package com.example.thefoodiezone.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.thefoodiezone.data.model.Businesses
import com.example.thefoodiezone.data.model.Category
import com.example.thefoodiezone.databinding.RestaurantListItemBinding

class RestaurantAdapter: RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    private val callBack = object : DiffUtil.ItemCallback<Businesses>(){
        override fun areItemsTheSame(oldItem: Businesses, newItem: Businesses): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Businesses, newItem: Businesses): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = RestaurantListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val businesses = differ.currentList[position]
        holder.bind(businesses)
    }

    inner class RestaurantViewHolder(val binding: RestaurantListItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(businesses: Businesses){
            binding.tvName.text = businesses.name
            binding.ratingBar.rating = businesses.rating.toFloat()
            binding.tvReviewCount.text = businesses.review_count.toString() + " reviews"
            binding.tvLocation.text = businesses.location.address1
            binding.tvDistance.text = displayDistance(businesses)
            binding.tvPhone.text = businesses.phone

            setAdapter(businesses.categories, binding)

            Glide.with(binding.ivBusinessImage.context)
                .load(businesses.imageUrl)
                .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(10)))
                .into(binding.ivBusinessImage)

        }
    }

    private fun setAdapter(categories: List<Category>, binding: RestaurantListItemBinding) {
        val categoryAdapter = FoodCategoryAdapter()
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.differ.submitList(categories.toMutableList())
    }

    private fun displayDistance(businesses: Businesses): String{
        val milesPerMeter = 0.000621371
        val distanceInMiles = "%.2f".format(businesses.distance * milesPerMeter)
        return "$distanceInMiles mi"
    }

}