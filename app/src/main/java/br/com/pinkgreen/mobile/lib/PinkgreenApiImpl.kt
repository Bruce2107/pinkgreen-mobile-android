package br.com.pinkgreen.mobile.lib

import br.com.pinkgreen.mkt.data.MktApi
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktSkuCodeResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PinkgreenApiImpl : MktApi {
    @GET("/product")
    override suspend fun fetchProducts(): Response<List<MktProductResponseDTO>>

    @GET("/product/{id}")
    override suspend fun fetchProduct(@Path("id") id: Int): Response<MktProductResponseDTO>

    @GET("/sku/product_skus/{id}")
    override suspend fun fetchSkuCode(@Path("id") id: Int): Response<List<MktSkuCodeResponseDTO>>
}