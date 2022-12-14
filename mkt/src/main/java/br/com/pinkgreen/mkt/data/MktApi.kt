package br.com.pinkgreen.mkt.data

import br.com.pinkgreen.mkt.data.dto.MktFavoriteResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktSkuCodeResponseDTO
import retrofit2.Response

interface MktApi {

    suspend fun fetchProduct(id: Int): Response<MktProductResponseDTO> {
        throw NotImplementedError("fetchProduct not implemented")
    }

    suspend fun fetchProducts(): Response<List<MktProductResponseDTO>> {
        throw NotImplementedError("fetchProducts not implemented")
    }

    suspend fun fetchSkuCode(id: Int): Response<List<MktSkuCodeResponseDTO>> {
        throw NotImplementedError("fetchSkuCode not implemented")
    }

    suspend fun postFavorite(sku: String): Response<Unit> {
        throw NotImplementedError("postFavorite not implemented")
    }

    suspend fun fetchFavorites(): Response<List<MktFavoriteResponseDTO>> {
        throw NotImplementedError("fetchFavorites not implemented")
    }

}