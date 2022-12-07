package br.com.pinkgreen.mkt.ui.view

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import br.com.pinkgreen.mkt.R
import br.com.pinkgreen.mkt.databinding.FragmentMktProductsBinding
import br.com.pinkgreen.mkt.di.CustomKoinComponent
import br.com.pinkgreen.mkt.ui.view.adpaters.MktProductListAdapter
import br.com.pinkgreen.mkt.ui.view.navigation.MktNavigation
import br.com.pinkgreen.mkt.ui.viewmodel.MktProductsViewModel
import br.com.pinkgreen.mkt.ui.viewstate.ErrorType
import br.com.pinkgreen.mkt.ui.viewstate.ViewState
import br.com.pinkgreen.mkt.ui.viewstate.collectIfNotNull
import br.com.pinkgreen.mkt.ui.vo.MktProductsResponseVO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class MktProductsFragment : Fragment(), CustomKoinComponent {
    private var _binding: FragmentMktProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MktProductsViewModel by viewModel()
    private val navigation: MktNavigation by inject { parametersOf(this) }

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
        setupVisibility()
        setupNavbar(requireActivity())
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
        setupVisibility(error = true)
        binding.mktErrorScreen.mktErrorTryAgain.setOnClickListener {
            action()
        }
    }

    private fun onProductsError(errorType: ErrorType) {
        onError(errorType) { viewModel.fetchProducts() }
    }

    private fun onProductsLoading() {
        setupVisibility(loading = true)
    }

    private fun onProductsSuccess(data: MktProductsResponseVO) {
        binding.mktProductList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.set(20, 12, 20, 12)
            }
        })
        binding.mktProductList.adapter =
            MktProductListAdapter(
                fragment = this,
                products = data.products,
                onClickListener = MktProductListAdapter.OnClickListener {
                    fastBuyAction(it.id)
                })
        setupVisibility(content = true)
    }

    private fun fastBuyAction(id: Int) {
        navigation.navigateFromListToDetails(id)
    }

    private fun setupNavbar(activity: FragmentActivity) = with(binding) {
        mktHomeNavbar.navbar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> viewModel.fetchProducts()
                R.id.action_cart -> navigation.navigateToCheckout()
                R.id.action_favorite -> navigation.navigateToFavourites(activity)
                R.id.action_settings -> navigation.navigateToSettings(activity)
                else -> viewModel.fetchProducts()
            }
            true
        }
    }

    private fun setupVisibility(loading: Boolean = false, content: Boolean = false, error: Boolean = false) =
        with(binding) {
            mktHomeLoading.root.isVisible = loading
            mktHomeContentGroup.isVisible = content
            mktErrorScreen.root.isVisible = error
            if (loading) {
                root.post {
                    mktHomeLoading.root.announceForAccessibility(
                        requireContext().getString(
                            R.string.loading
                        )
                    )
                }
            }
        }

}