package br.com.pinkgreen.mkt.data.repository

import br.com.pinkgreen.mkt.data.MktApi
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.commons.extension.flowOf
import kotlinx.coroutines.flow.Flow

internal class MktProductRepositoryImpl(private val productMktApi: MktApi) : MktProductRepository {
    override fun fetchProduct(id: String): Flow<MktProductResponseDTO> = flowOf {
        productMktApi.fetchProduct(id)
    }

    override fun fetchProducts(): Flow<List<MktProductResponseDTO>> = flowOf {
        productMktApi.fetchProducts()
    }
}