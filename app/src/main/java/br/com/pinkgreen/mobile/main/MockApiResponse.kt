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
                        MktProductResponseDTO(name = "produto 1", price = 12.2),
                        MktProductResponseDTO(name = "produto 2", price = 10.2)
                    )
                )
            )
        }
    }


    val error: Response<Any> by lazy {
        Response.error(400, ResponseBody.create(null, "Bad Request"))
    }
}