package br.com.pinkgreen.mkt.data.dto

import com.google.gson.annotations.SerializedName

class MktProductResponseDTO(
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double
)