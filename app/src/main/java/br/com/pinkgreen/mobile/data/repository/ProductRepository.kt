package br.com.pinkgreen.mobile.data.repository

import br.com.pinkgreen.mobile.data.dto.ProductResponseDTO
import br.com.pinkgreen.mobile.data.dto.ProductsResponseDTO
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun fetchProduct(id: String): Flow<ProductResponseDTO>
    fun fetchProducts(): Flow<ProductsResponseDTO>
}