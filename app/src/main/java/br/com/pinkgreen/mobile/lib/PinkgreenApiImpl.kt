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
                    MktProductResponseDTO(
                        name = "produto 1",
                        price = 12.2,
                        id = "13fefdb1-0b68-4eee-a721-56e1926586b7"
                    ),
                    MktProductResponseDTO(
                        name = "produto 2",
                        price = 10.2,
                        id = "a7f5fb9d-a143-4da5-bfeb-aadf6027557c"
                    )
                )
            )
        )
    }

}