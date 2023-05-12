package com.example.thefoodiezone.data.model

data class APIResponse(
    val businesses: List<Businesses>,
    val region: Region,
    val total: Int
)