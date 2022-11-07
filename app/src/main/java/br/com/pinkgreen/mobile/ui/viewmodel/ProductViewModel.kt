package br.com.pinkgreen.mobile.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.pinkgreen.mobile.commons.BaseViewModel
import br.com.pinkgreen.mobile.data.repository.ProductRepository
import br.com.pinkgreen.mobile.domain.converter.ProductConverter
import br.com.pinkgreen.mobile.ui.viewstate.ViewState
import br.com.pinkgreen.mobile.ui.viewstate.collectViewState
import br.com.pinkgreen.mobile.ui.vo.ProductResponseVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository,
    private val converter: ProductConverter
) : BaseViewModel() {
    private val _product = MutableStateFlow<ViewState<ProductResponseVO>?>(null)
    val product: StateFlow<ViewState<ProductResponseVO>?> = _product

    fun fetchProduct(id: String) = viewModelScope.launch {
        repository.fetchProduct(id).map { converter.convert(it) }.collectViewState(_product)
    }
}