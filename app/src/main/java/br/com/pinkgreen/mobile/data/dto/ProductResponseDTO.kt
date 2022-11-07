package br.com.pinkgreen.mobile.data.dto

import com.google.gson.annotations.SerializedName

class ProductResponseDTO(
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double
)