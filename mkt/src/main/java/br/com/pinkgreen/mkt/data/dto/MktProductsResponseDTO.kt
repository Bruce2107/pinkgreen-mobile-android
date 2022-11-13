package br.com.pinkgreen.mkt.data.dto

import com.google.gson.annotations.SerializedName

class MktProductsResponseDTO(
    @SerializedName("products") val products: List<MktProductResponseDTO>
)