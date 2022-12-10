package br.com.pinkgreen.mkt.domain.converter

import br.com.pinkgreen.mkt.data.dto.MktFavoriteResponseDTO
import br.com.pinkgreen.mkt.ui.vo.MktFavoritesResponseVO
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO

internal class MktFavoriteConverter {
    fun convert(dto: List<MktFavoriteResponseDTO>) =
        MktFavoritesResponseVO(
            favorites = dto.convert()
        )

    private fun List<MktFavoriteResponseDTO>.convert() = map {
        MktProductResponseVO(
            name = it.product.name,
            price = it.product.price,
            id = it.product.id,
            active = it.product.active,
            mainImage = it.product.mainImageUrl,
            categories = null,
            brand = null
        )
    }
}