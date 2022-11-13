package br.com.pinkgreen.mkt.data

import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductsResponseDTO
import retrofit2.Response

interface MktApi {

    suspend fun fetchProduct(id: String): Response<MktProductResponseDTO> {
        throw NotImplementedError("fetchProduct not implemented")
    }

    suspend fun fetchProducts(): Response<MktProductsResponseDTO> {
        throw NotImplementedError("fetchProducts not implemented")
    }

}