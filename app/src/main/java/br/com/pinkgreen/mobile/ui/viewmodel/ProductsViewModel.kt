package br.com.pinkgreen.mobile.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.pinkgreen.mobile.commons.BaseViewModel
import br.com.pinkgreen.mobile.data.repository.ProductRepository
import br.com.pinkgreen.mobile.domain.converter.ProductConverter
import br.com.pinkgreen.mobile.domain.converter.ProductsConverter
import br.com.pinkgreen.mobile.ui.viewstate.ViewState
import br.com.pinkgreen.mobile.ui.viewstate.collectViewState
import br.com.pinkgreen.mobile.ui.vo.ProductResponseVO
import br.com.pinkgreen.mobile.ui.vo.ProductsResponseVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val repository: ProductRepository,
    private val converter: ProductsConverter
) : BaseViewModel() {
    private val _products = MutableStateFlow<ViewState<ProductsResponseVO>?>(null)
    val products: StateFlow<ViewState<ProductsResponseVO>?> = _products

    fun fetchProducts() = viewModelScope.launch {
        repository.fetchProducts().map { converter.convert(it) }.collectViewState(_products)
    }
}