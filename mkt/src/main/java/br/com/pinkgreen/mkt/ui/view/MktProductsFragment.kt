package br.com.pinkgreen.mkt.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.pinkgreen.mkt.databinding.FragmentMktProductsBinding
import br.com.pinkgreen.mkt.di.CustomKoinComponent
import br.com.pinkgreen.mkt.ui.components.error.ErrorLauncherParams
import br.com.pinkgreen.mkt.ui.view.adpaters.MktProductListAdapter
import br.com.pinkgreen.mkt.ui.viewmodel.MktProductsViewModel
import br.com.pinkgreen.mkt.ui.viewstate.ErrorType
import br.com.pinkgreen.mkt.ui.viewstate.ViewState
import br.com.pinkgreen.mkt.ui.viewstate.collectIfNotNull
import br.com.pinkgreen.mkt.ui.viewstate.vo.MktProductsResponseVO
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

internal class MktProductsFragment : Fragment(), CustomKoinComponent {
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
        setupObservers()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.products.collectIfNotNull {
                        when (it) {
                            ViewState.OnLoading -> onProductsLoading()
                            is ViewState.OnError -> onProductsError(it.errorType)
                            is ViewState.OnSuccess -> onProductsSuccess(it.data)
                        }
                    }
                }
            }
        }
    }

    private fun onError(errorType: ErrorType, action: () -> (Unit)) {
        val errorLauncherParams = ErrorLauncherParams(
            fragmentActivity = WeakReference(this),
            onDismissClickListener = { requireActivity().finish() },
            onPrimaryButtonClickListener = action
        )
    }

    private fun onProductsError(errorType: ErrorType) {
        onError(errorType) { viewModel.fetchProducts() }
    }

    private fun onProductsLoading() {
    }

    private fun onProductsSuccess(data: MktProductsResponseVO) {
        binding.mktProductList.adapter =
            MktProductListAdapter(activity?.applicationContext!!, data.products)
    }

}