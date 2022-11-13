package br.com.pinkgreen.mobile.lib

import br.com.pinkgreen.mkt.data.MktApi
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductsResponseDTO
import retrofit2.Response

class PinkgreenApiImpl : MktApi {
    override suspend fun fetchProducts(): Response<MktProductsResponseDTO> {
        return Response.success(
            MktProductsResponseDTO(
                listOf(
                    MktProductResponseDTO(name = "produto 1", price = 12.2),
                    MktProductResponseDTO(name = "produto 2", price = 10.2)
                )
            )
        )
    }

}