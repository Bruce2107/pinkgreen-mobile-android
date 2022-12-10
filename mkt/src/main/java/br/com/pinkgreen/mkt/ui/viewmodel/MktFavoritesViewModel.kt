package br.com.pinkgreen.mkt.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.pinkgreen.mkt.commons.BaseViewModel
import br.com.pinkgreen.mkt.data.repository.MktProductRepository
import br.com.pinkgreen.mkt.domain.converter.MktFavoriteConverter
import br.com.pinkgreen.mkt.ui.viewstate.ViewState
import br.com.pinkgreen.mkt.ui.viewstate.collectViewState
import br.com.pinkgreen.mkt.ui.vo.MktFavoritesResponseVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class MktFavoritesViewModel(
    private val repository: MktProductRepository,
    private val converter: MktFavoriteConverter
) : BaseViewModel() {
    private val _favorites = MutableStateFlow<ViewState<MktFavoritesResponseVO>?>(null)
    val favorites: StateFlow<ViewState<MktFavoritesResponseVO>?> = _favorites

    fun fetchFavorites() = viewModelScope.launch {
        repository.fetchFavorites().map { converter.convert(it) }.collectViewState(_favorites)
    }
}