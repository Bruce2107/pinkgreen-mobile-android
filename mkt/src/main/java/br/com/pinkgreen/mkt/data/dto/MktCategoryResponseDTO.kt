package br.com.pinkgreen.mkt.data.dto

import com.google.gson.annotations.SerializedName

class MktCategoryResponseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)