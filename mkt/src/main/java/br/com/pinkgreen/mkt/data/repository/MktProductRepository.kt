package br.com.pinkgreen.mkt.data.repository

import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductsResponseDTO
import kotlinx.coroutines.flow.Flow

internal interface MktProductRepository {
    fun fetchProduct(id: String): Flow<MktProductResponseDTO>
    fun fetchProducts(): Flow<MktProductsResponseDTO>
}