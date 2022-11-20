package br.com.pinkgreen.mkt.ui.vo

internal class MktProductResponseVO(
    val id: Int,
    val name: String,
    val price: Double,
    val active: Boolean,
    val mainImage: String,
    val brand: MktBrandResponseVO,
    val categories: List<MktCategoryResponseVO>
)