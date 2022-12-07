package br.com.pinkgreen.mkt.domain.converter

import br.com.pinkgreen.mkt.data.dto.MktBrandResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktCategoryResponseDTO
import br.com.pinkgreen.mkt.data.dto.MktProductResponseDTO
import br.com.pinkgreen.mkt.ui.vo.MktBrandResponseVO
import br.com.pinkgreen.mkt.ui.vo.MktCategoryResponseVO
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO
import br.com.pinkgreen.mkt.ui.vo.MktProductsResponseVO

internal class MktProductsConverter {
    fun convert(dto: List<MktProductResponseDTO>) =
        MktProductsResponseVO(products = dto.convert())

    fun convertDB(db: List<MktProductResponseVO>) =
        MktProductsResponseVO(products = db.convertDB())

    private fun List<MktProductResponseDTO>.convert() = map {
        MktProductResponseVO(
            name = it.name,
            price = it.price,
            id = it.id,
            active = it.active,
            mainImage = it.mainImageUrl,
            categories = it.categories.convertCategory(),
            brand = it.brand.convert()
        )
    }

    private fun MktBrandResponseDTO.convert() = MktBrandResponseVO(
        id, name, brandImage
    )

    private fun List<MktCategoryResponseDTO>.convertCategory() = map {
        MktCategoryResponseVO(name = it.name, id = it.id, image = it.image)
    }

    private fun List<MktProductResponseVO>.convertDB() = map {
        MktProductResponseVO(
            name = it.name,
            price = it.price,
            id = it.id,
            active = null,
            mainImage = it.mainImage,
            categories = null,
            brand = null
        )
    }

}