package br.com.pinkgreen.mkt.domain.converter

import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.ui.viewstate.vo.MktProductResponseVO

internal class MktProductConverter {
    fun convert(dto: MktProductResponseDTO) = MktProductResponseVO(name = dto.name, price = dto.price, image = null)
    fun List<MktProductResponseDTO>.convert() = map {
        MktProductResponseVO(name = it.name, price = it.price, image = null)
    }
}