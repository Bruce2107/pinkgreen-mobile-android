package br.com.pinkgreen.mobile.main

import br.com.pinkgreen.mkt.data.MktApi
import br.com.pinkgreen.mkt.data.dto.MktProductsResponseDTO
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchProductsOption
import kotlinx.coroutines.delay
import retrofit2.Response

class MockApi(
    private val fetchProducts: PinkgreenFetchProductsOption
) : MktApi {
    override suspend fun fetchProducts(): Response<MktProductsResponseDTO> {
        delay(2000)
        return fetchProducts.response
    }
}