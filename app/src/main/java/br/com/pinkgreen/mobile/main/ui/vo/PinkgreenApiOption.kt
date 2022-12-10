package br.com.pinkgreen.mobile.main.ui.vo

import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktSkuCodeResponseDTO
import retrofit2.Response

sealed class PinkgreenApiOption(val title: String)

class PinkgreenFetchProductsOption(
    title: String,
    var response: Response<List<MktProductResponseDTO>>
) : PinkgreenApiOption(title)

class PinkgreenFetchProductOption(
    title: String,
    var response: Response<MktProductResponseDTO>
) : PinkgreenApiOption(title)

class PinkgreenFetchSkuCodeOption(
    title: String,
    var response: Response<List<MktSkuCodeResponseDTO>>
) : PinkgreenApiOption(title)

