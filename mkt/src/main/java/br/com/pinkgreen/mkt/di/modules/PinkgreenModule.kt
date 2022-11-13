package br.com.pinkgreen.mkt.di.modules

import br.com.pinkgreen.mkt.data.repository.MktProductRepository
import br.com.pinkgreen.mkt.data.repository.MktProductRepositoryImpl
import br.com.pinkgreen.mkt.domain.converter.MktProductConverter
import br.com.pinkgreen.mkt.domain.converter.MktProductsConverter
import br.com.pinkgreen.mkt.ui.viewmodel.MktProductViewModel
import br.com.pinkgreen.mkt.ui.viewmodel.MktProductsViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

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

//
//    factory { (fragment: Fragment) ->
//        InternshipNavigation(WeakReference(fragment.findNavController()))
//    }
//

    viewModel {
        MktProductsViewModel(get(), get())
    }

    viewModel {
        MktProductViewModel(get(), get())
    }

}