package br.com.pinkgreen.mkt.data.dto

import com.google.gson.annotations.SerializedName

class MktBrandResponseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("brandImage") val brandImage: String
)