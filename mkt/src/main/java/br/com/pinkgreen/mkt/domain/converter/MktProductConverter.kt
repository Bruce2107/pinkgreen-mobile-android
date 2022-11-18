package br.com.pinkgreen.mkt.domain.converter

import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO

internal class MktProductConverter {
    fun convert(dto: MktProductResponseDTO) =
        MktProductResponseVO(id = dto.id, name = dto.name, price = dto.price)

    fun List<MktProductResponseDTO>.convert() = map {
        MktProductResponseVO(name = it.name, price = it.price, id = it.id)
    }
}