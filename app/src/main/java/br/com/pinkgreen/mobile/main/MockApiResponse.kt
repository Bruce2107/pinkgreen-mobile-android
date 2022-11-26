package br.com.pinkgreen.mobile.main

import br.com.pinkgreen.mkt.data.dto.MktBrandResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktCategoryResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object MockApiResponse {
    object MktProducts {
        val fetchProducts: Response<List<MktProductResponseDTO>> by lazy {
            Response.success(

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
                    ), MktProductResponseDTO(
                        id = 3,
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
                    ), MktProductResponseDTO(
                        id = 4,
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
                    ), MktProductResponseDTO(
                        id = 5,
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
                    ), MktProductResponseDTO(
                        id = 6,
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
                    ), MktProductResponseDTO(
                        id = 7,
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

        }
    }


    val error: Response<Any> by lazy {
        Response.error(400, "Bad Request".toResponseBody(null))
    }
}