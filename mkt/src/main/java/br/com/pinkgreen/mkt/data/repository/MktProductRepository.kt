package br.com.pinkgreen.mkt.data.repository

import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import kotlinx.coroutines.flow.Flow

internal interface MktProductRepository {
    fun fetchProduct(id: Int): Flow<MktProductResponseDTO>
    fun fetchProducts(): Flow<List<MktProductResponseDTO>>
}