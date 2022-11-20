package br.com.pinkgreen.mkt.domain.converter

import br.com.pinkgreen.mkt.data.dto.MktBrandResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktCategoryResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.ui.vo.MktBrandResponseVO
import br.com.pinkgreen.mkt.ui.vo.MktCategoryResponseVO
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO

internal class MktProductConverter {
    fun convert(dto: MktProductResponseDTO) =
        MktProductResponseVO(
            id = dto.id,
            name = dto.name,
            price = dto.price,
            active = dto.active,
            mainImage = dto.mainImageUrl,
            brand = dto.brand.convert(),
            categories = dto.categories.convertCategory()
        )

    private fun MktBrandResponseDTO.convert() = MktBrandResponseVO(
        id, name, brandImage
    )

    private fun List<MktCategoryResponseDTO>.convertCategory() = map {
        MktCategoryResponseVO(name = it.name, id = it.id, image = it.image)
    }
}