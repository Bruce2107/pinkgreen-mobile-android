package br.com.pinkgreen.mobile.domain.converter

import br.com.pinkgreen.mobile.data.dto.ProductResponseDTO
import br.com.pinkgreen.mobile.ui.vo.ProductResponseVO

class ProductConverter {
    fun convert(dto: ProductResponseDTO) = ProductResponseVO(name = dto.name, price = dto.price, image = null)
    fun List<ProductResponseDTO>.convert() = map {
        ProductResponseVO(name = it.name, price = it.price, image = null)
    }
}