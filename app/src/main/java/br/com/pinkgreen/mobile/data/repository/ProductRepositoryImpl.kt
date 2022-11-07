package br.com.pinkgreen.mobile.data.repository

import br.com.pinkgreen.mobile.data.Api
import br.com.pinkgreen.mobile.data.dto.ProductResponseDTO
import br.com.pinkgreen.mobile.commons.extension.flowOf
import br.com.pinkgreen.mobile.data.dto.ProductsResponseDTO
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(private val productApi: Api) : ProductRepository {
    override fun fetchProduct(id: String): Flow<ProductResponseDTO> = flowOf {
        productApi.fetchProduct(id)
    }

    override fun fetchProducts(): Flow<ProductsResponseDTO> = flowOf {
        productApi.fetchProducts()
    }
}