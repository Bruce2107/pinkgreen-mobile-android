package br.com.pinkgreen.mkt.data.dto

import com.google.gson.annotations.SerializedName

class MktProductResponseDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("active") val active: Boolean,
    @SerializedName("mainImageUrl") val mainImageUrl: String,
    @SerializedName("brand") val brand: MktBrandResponseDTO,
    @SerializedName("categories")val categories: List<MktCategoryResponseDTO>
)