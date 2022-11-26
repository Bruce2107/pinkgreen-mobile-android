package br.com.pinkgreen.mobile.main.ui.vo

import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import retrofit2.Response

sealed class PinkgreenApiOption(val title: String)

class PinkgreenFetchProductsOption(
    title: String,
    var response: Response<List<MktProductResponseDTO>>
) : PinkgreenApiOption(title)

