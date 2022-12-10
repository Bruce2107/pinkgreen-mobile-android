package br.com.pinkgreen.mkt.data.repository

import br.com.pinkgreen.mkt.data.MktApi
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.commons.extension.flowOf
import br.com.pinkgreen.mkt.data.dto.MktFavoriteResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktSkuCodeResponseDTO
import kotlinx.coroutines.flow.Flow

internal class MktProductRepositoryImpl(private val productMktApi: MktApi) : MktProductRepository {
    override fun fetchProduct(id: Int): Flow<MktProductResponseDTO> = flowOf {
        productMktApi.fetchProduct(id)
    }

    override fun fetchProducts(): Flow<List<MktProductResponseDTO>> = flowOf {
        productMktApi.fetchProducts()
    }

    override fun fetchSkuCode(id: Int): Flow<List<MktSkuCodeResponseDTO>> = flowOf {
        productMktApi.fetchSkuCode(id)
    }

    override fun postFavorite(sku: String): Flow<Unit> = flowOf {
        productMktApi.postFavorite(sku)
    }

    override fun fetchFavorites(): Flow<List<MktFavoriteResponseDTO>> = flowOf {
        productMktApi.fetchFavorites()
    }
}