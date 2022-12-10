package br.com.pinkgreen.mkt.data.dto

import com.google.gson.annotations.SerializedName

class MktFavoriteResponseDTO(
    @SerializedName("skuCode") val skuCode: String,
    @SerializedName("product") val product: MktProductResponseDTO
) {
}