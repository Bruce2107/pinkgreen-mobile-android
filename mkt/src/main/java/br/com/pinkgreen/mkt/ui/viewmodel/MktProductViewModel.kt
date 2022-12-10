package br.com.pinkgreen.mkt.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.pinkgreen.mkt.commons.BaseViewModel
import br.com.pinkgreen.mkt.data.repository.MktProductRepository
import br.com.pinkgreen.mkt.domain.converter.MktProductConverter
import br.com.pinkgreen.mkt.domain.converter.MktSkuCodeConverter
import br.com.pinkgreen.mkt.ui.viewstate.ViewState
import br.com.pinkgreen.mkt.ui.viewstate.collectViewState
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO
import br.com.pinkgreen.mkt.ui.vo.MktSkusCodeReponseVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class MktProductViewModel(
    private val repository: MktProductRepository,
    private val converter: MktProductConverter,
    private val skuConverter: MktSkuCodeConverter
) : BaseViewModel() {
    private val _product = MutableStateFlow<ViewState<MktProductResponseVO>?>(null)
    val product: StateFlow<ViewState<MktProductResponseVO>?> = _product

    private val _sku = MutableStateFlow<ViewState<MktSkusCodeReponseVO>?>(null)
    val sku: StateFlow<ViewState<MktSkusCodeReponseVO>?> = _sku

    private val _favorite = MutableStateFlow<ViewState<Unit>?>(null)
    val favorite: StateFlow<ViewState<Unit>?> = _favorite

    fun fetchProduct(id: Int) = viewModelScope.launch {
        repository.fetchProduct(id).map { converter.convert(it) }.collectViewState(_product)
    }

    fun fetchSkuCode(id: Int) = viewModelScope.launch {
        repository.fetchSkuCode(id).map { skuConverter.convert(it) }.collectViewState(_sku)
    }

    fun postFavorite(sku: String) = viewModelScope.launch {
        repository.postFavorite(sku).collectViewState(_favorite)
    }
}