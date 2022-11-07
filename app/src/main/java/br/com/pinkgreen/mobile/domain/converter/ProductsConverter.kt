package br.com.pinkgreen.mobile.domain.converter

import br.com.pinkgreen.mobile.data.dto.ProductResponseDTO
import br.com.pinkgreen.mobile.data.dto.ProductsResponseDTO
import br.com.pinkgreen.mobile.ui.vo.ProductResponseVO
import br.com.pinkgreen.mobile.ui.vo.ProductsResponseVO

class ProductsConverter {
    fun convert(dto: ProductsResponseDTO) = ProductsResponseVO(products = dto.products.convert())
    private fun List<ProductResponseDTO>.convert() = map {
        ProductResponseVO(name = it.name, price = it.price, image = null)
    }
}