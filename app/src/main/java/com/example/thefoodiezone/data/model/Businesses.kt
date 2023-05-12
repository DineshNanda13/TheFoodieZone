package com.example.thefoodiezone.data.model

import com.google.gson.annotations.SerializedName

data class Businesses(
    val alias: String,
    val categories: List<Category>,
    val coordinates: Coordinates,
    @SerializedName("display_phone")
    val displayPhone: String,
    val distance: Double,
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("is_closed")
    val isClosed: Boolean,
    val location: Location,
    val name: String,
    val phone: String,
    val price: String,
    val rating: Double,
    val review_count: Int,
    val transactions: List<String>,
    val url: String
)