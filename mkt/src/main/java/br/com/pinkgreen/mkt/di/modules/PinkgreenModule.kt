package br.com.pinkgreen.mkt.di.modules

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.pinkgreen.mkt.data.repository.MktProductRepository
import br.com.pinkgreen.mkt.data.repository.MktProductRepositoryImpl
import br.com.pinkgreen.mkt.database.MktDBHelper
import br.com.pinkgreen.mkt.database.MktDBQueries
import br.com.pinkgreen.mkt.domain.converter.MktFavoriteConverter
import br.com.pinkgreen.mkt.domain.converter.MktProductConverter
import br.com.pinkgreen.mkt.domain.converter.MktProductsConverter
import br.com.pinkgreen.mkt.domain.converter.MktSkuCodeConverter
import br.com.pinkgreen.mkt.ui.view.navigation.MktNavigation
import br.com.pinkgreen.mkt.ui.viewmodel.MktCheckoutViewModel
import br.com.pinkgreen.mkt.ui.viewmodel.MktFavoritesViewModel
import br.com.pinkgreen.mkt.ui.viewmodel.MktProductViewModel
import br.com.pinkgreen.mkt.ui.viewmodel.MktProductsViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.ref.WeakReference

val mktModule = module {
    factory<MktProductRepository> {
        MktProductRepositoryImpl(get())
    }
    factory {
        MktProductsConverter()
    }

    factory {
        MktProductConverter()
    }

    factory {
        MktSkuCodeConverter()
    }

    factory {
        MktFavoriteConverter()
    }

    factory { (fragment: Fragment) ->
        MktNavigation(WeakReference(fragment.findNavController()))
    }

    factory {
        MktDBHelper(get())
    }

    factory {
        MktDBQueries(get())
    }


    viewModel {
        MktProductsViewModel(get(), get())
    }

    viewModel {
        MktProductViewModel(get(), get(), get())
    }

    viewModel {
        MktCheckoutViewModel(get(), get())
    }

    viewModel {
        MktFavoritesViewModel(get(), get())
    }

}