package br.com.pinkgreen.mkt.domain.converter

import br.com.pinkgreen.mkt.data.dto.MktSkuCodeResponseDTO
import br.com.pinkgreen.mkt.ui.vo.MktSkuCodeResponseVO
import br.com.pinkgreen.mkt.ui.vo.MktSkusCodeReponseVO

internal class MktSkuCodeConverter {
    fun convert(dto: List<MktSkuCodeResponseDTO>) = MktSkusCodeReponseVO(
        skus = dto.convert()
    )


    private fun List<MktSkuCodeResponseDTO>.convert() = map {
        MktSkuCodeResponseVO(
            skuCode = it.skuCode
        )
    }
}