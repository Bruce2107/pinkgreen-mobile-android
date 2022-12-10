package br.com.pinkgreen.mobile.main

import br.com.pinkgreen.mkt.data.MktApi
import br.com.pinkgreen.mkt.data.dto.MktFavoriteResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktSkuCodeResponseDTO
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchFavoritesOption
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchProductOption
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchProductsOption
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchSkuCodeOption
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenPostFavoriteOption
import kotlinx.coroutines.delay
import retrofit2.Response

class MockApi(
    private val fetchProducts: PinkgreenFetchProductsOption,
    private val fetchProduct: PinkgreenFetchProductOption,
    private val fetchSkuCode: PinkgreenFetchSkuCodeOption,
    private val postFavorite: PinkgreenPostFavoriteOption,
    private val fetchFavorites: PinkgreenFetchFavoritesOption
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

    override suspend fun postFavorite(sku: String): Response<Unit> {
        delay(500)
        return postFavorite.response
    }

    override suspend fun fetchFavorites(): Response<List<MktFavoriteResponseDTO>> {
        delay(500)
        return fetchFavorites.response
    }
}