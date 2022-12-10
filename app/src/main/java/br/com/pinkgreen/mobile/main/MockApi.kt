package br.com.pinkgreen.mobile.main

import br.com.pinkgreen.mkt.data.MktApi
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktSkuCodeResponseDTO
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchProductOption
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchProductsOption
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchSkuCodeOption
import kotlinx.coroutines.delay
import retrofit2.Response

class MockApi(
    private val fetchProducts: PinkgreenFetchProductsOption,
    private val fetchProduct: PinkgreenFetchProductOption,
    private val fetchSkuCode: PinkgreenFetchSkuCodeOption
) : MktApi {
    override suspend fun fetchProducts(): Response<List<MktProductResponseDTO>> {
        delay(500)
        return fetchProducts.response
    }

    override suspend fun fetchProduct(id: Int): Response<MktProductResponseDTO> {
        delay(500)
        return fetchProduct.response
    }

    override suspend fun fetchSkuCode(id: Int): Response<List<MktSkuCodeResponseDTO>> {
        delay(500)
        return fetchSkuCode.response
    }
}