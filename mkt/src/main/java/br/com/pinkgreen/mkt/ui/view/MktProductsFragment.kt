package br.com.pinkgreen.mkt.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.pinkgreen.mkt.databinding.FragmentMktProductsBinding
import br.com.pinkgreen.mkt.di.CustomKoinComponent
import br.com.pinkgreen.mkt.ui.viewmodel.MktProductsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MktProductsFragment: Fragment(), CustomKoinComponent {
    private var _binding: FragmentMktProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MktProductsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMktProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchProducts()
    }

}