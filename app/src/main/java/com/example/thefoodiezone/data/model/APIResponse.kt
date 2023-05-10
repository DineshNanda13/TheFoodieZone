package com.example.thefoodiezone.data.model

data class APIResponse(
    val businesses: List<Businesse>,
    val region: Region,
    val total: Int
)