package br.com.pinkgreen.mobile.data

import br.com.pinkgreen.mobile.data.dto.ProductResponseDTO
import br.com.pinkgreen.mobile.data.dto.ProductsResponseDTO
import retrofit2.Response

interface Api {

    suspend fun fetchProduct(id: String): Response<ProductResponseDTO> {
        throw NotImplementedError("fetchProduct not implemented")
    }

    suspend fun fetchProducts(): Response<ProductsResponseDTO> {
        throw NotImplementedError("fetchProducts not implemented")
    }

}