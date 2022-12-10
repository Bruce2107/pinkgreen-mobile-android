package br.com.pinkgreen.mobile.lib

import br.com.pinkgreen.mkt.data.MktApi
import br.com.pinkgreen.mkt.data.dto.MktFavoriteResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktSkuCodeResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PinkgreenApiImpl : MktApi {
    @GET("/product")
    override suspend fun fetchProducts(): Response<List<MktProductResponseDTO>>

    @GET("/product/{id}")
    override suspend fun fetchProduct(@Path("id") id: Int): Response<MktProductResponseDTO>

    @GET("/sku/product_skus/{id}")
    override suspend fun fetchSkuCode(@Path("id") id: Int): Response<List<MktSkuCodeResponseDTO>>

    @POST("/favorite/product/{sku}/user/e1503b98-4e3c-41fb-9faf-bc4c80368796")
    override suspend fun postFavorite(@Path("sku") sku: String): Response<Unit>

    @GET("/favorite/user/e1503b98-4e3c-41fb-9faf-bc4c80368796")
    override suspend fun fetchFavorites(): Response<List<MktFavoriteResponseDTO>>
}