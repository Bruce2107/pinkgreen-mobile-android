package br.com.pinkgreen.mobile.data.dto

import com.google.gson.annotations.SerializedName

class ProductsResponseDTO(
    @SerializedName("products") val products: List<ProductResponseDTO>
)