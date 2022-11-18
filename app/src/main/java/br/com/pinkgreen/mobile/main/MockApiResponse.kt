package br.com.pinkgreen.mobile.main

import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductsResponseDTO
import okhttp3.ResponseBody
import retrofit2.Response

object MockApiResponse {
    object MktProducts {
        val fetchProducts: Response<MktProductsResponseDTO> by lazy {
            Response.success(
                MktProductsResponseDTO(
                    listOf(
                        MktProductResponseDTO(
                            name = "produto 1", price = 12.2,
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


    val error: Response<Any> by lazy {
        Response.error(400, ResponseBody.create(null, "Bad Request"))
    }
}