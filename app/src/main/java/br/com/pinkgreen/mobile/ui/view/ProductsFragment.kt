package br.com.pinkgreen.mobile.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.pinkgreen.mobile.databinding.FragmentProductsBinding
import br.com.pinkgreen.mobile.di.CustomKoinComponent
import br.com.pinkgreen.mobile.ui.viewmodel.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFragment: Fragment(), CustomKoinComponent {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchProducts()
    }

}