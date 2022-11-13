package br.com.pinkgreen.mkt.domain.converter

import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductsResponseDTO
import br.com.pinkgreen.mkt.ui.viewstate.vo.MktProductResponseVO
import br.com.pinkgreen.mkt.ui.viewstate.vo.MktProductsResponseVO

internal class MktProductsConverter {
    fun convert(dto: MktProductsResponseDTO) = MktProductsResponseVO(products = dto.products.convert())
    private fun List<MktProductResponseDTO>.convert() = map {
        MktProductResponseVO(name = it.name, price = it.price, image = null)
    }
}