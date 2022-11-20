package br.com.pinkgreen.mobile.lib

import br.com.pinkgreen.mkt.data.MktApi
import br.com.pinkgreen.mkt.data.dto.MktBrandResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktCategoryResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductsResponseDTO
import retrofit2.Response

class PinkgreenApiImpl : MktApi {
    override suspend fun fetchProducts(): Response<MktProductsResponseDTO> {
        return Response.success(
            MktProductsResponseDTO(
                listOf(
                    MktProductResponseDTO(
                        id = 1,
                        name = "Notebook Aspire 5",
                        price = 3704.05,
                        active = true,
                        mainImageUrl = "https://i.imgur.com/PchRPP7.png",
                        brand = MktBrandResponseDTO(
                            id = 1,
                            name = "Acer",
                            brandImage = "https://i.imgur.com/6wTcxmU.png"
                        ),
                        categories = listOf(
                            MktCategoryResponseDTO(
                                id = 1,
                                name = "Informatica",
                                image = "https://i.imgur.com/PchRPP7.png",
                            )
                        )
                    ), MktProductResponseDTO(
                        id = 2,
                        name = "Galaxy S10e",
                        price = 2400.0,
                        active = true,
                        mainImageUrl = "https://i.imgur.com/dkR4vDn.png",
                        brand = MktBrandResponseDTO(
                            id = 3,
                            name = "Samsung",
                            brandImage = "https://i.imgur.com/OkgdSou.png"
                        ),
                        categories = listOf(
                            MktCategoryResponseDTO(
                                id = 3,
                                name = "Celulares e smartphones",
                                image = "https://i.imgur.com/m6VyNEE.png"

                            )
                        )
                    )
                )
            )
        )
    }

}