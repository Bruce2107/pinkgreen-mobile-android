package br.com.pinkgreen.mobile.di.modules

import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.pinkgreen.mobile.data.repository.ProductRepository
import br.com.pinkgreen.mobile.data.repository.ProductRepositoryImpl
import br.com.pinkgreen.mobile.domain.converter.ProductConverter
import br.com.pinkgreen.mobile.domain.converter.ProductsConverter
import br.com.pinkgreen.mobile.ui.viewmodel.ProductViewModel
import br.com.pinkgreen.mobile.ui.viewmodel.ProductsViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.lang.ref.WeakReference

val pinkgreenModule = module {
    factory<ProductRepository> {
        ProductRepositoryImpl(get())
    }
    factory {
        ProductsConverter()
    }

    factory {
        ProductConverter()
    }

//
//    factory { (fragment: Fragment) ->
//        InternshipNavigation(WeakReference(fragment.findNavController()))
//    }
//

    viewModel {
        ProductsViewModel(get(), get())
    }

    viewModel {
        ProductViewModel(get(), get())
    }

}