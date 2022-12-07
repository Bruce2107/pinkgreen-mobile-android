package br.com.pinkgreen.mkt.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.pinkgreen.mkt.commons.BaseViewModel
import br.com.pinkgreen.mkt.database.MktDBQueries
import br.com.pinkgreen.mkt.domain.converter.MktProductsConverter
import br.com.pinkgreen.mkt.ui.viewstate.ViewState
import br.com.pinkgreen.mkt.ui.viewstate.collectViewState
import br.com.pinkgreen.mkt.ui.vo.MktProductsResponseVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class MktCheckoutViewModel(
    private val queries: MktDBQueries,
    private val converter: MktProductsConverter
) : BaseViewModel() {
    private val _products = MutableStateFlow<ViewState<MktProductsResponseVO>?>(null)
    val products: StateFlow<ViewState<MktProductsResponseVO>?> = _products

    fun fetchProducts() = viewModelScope.launch {
        queries.getCart().map { converter.convertDB(it) }.collectViewState(_products)
    }
}