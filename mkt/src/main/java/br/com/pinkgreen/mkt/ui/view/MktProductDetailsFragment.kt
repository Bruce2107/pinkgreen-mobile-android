package br.com.pinkgreen.mkt.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.pinkgreen.mkt.R
import br.com.pinkgreen.mkt.database.MktDBHelper
import br.com.pinkgreen.mkt.database.MktDBQueries
import br.com.pinkgreen.mkt.databinding.FragmentMktProductDetailsBinding
import br.com.pinkgreen.mkt.di.CustomKoinComponent
import br.com.pinkgreen.mkt.ui.view.navigation.MktNavigation
import br.com.pinkgreen.mkt.ui.viewmodel.MktProductViewModel
import br.com.pinkgreen.mkt.ui.viewstate.ErrorType
import br.com.pinkgreen.mkt.ui.viewstate.ViewState
import br.com.pinkgreen.mkt.ui.viewstate.collectIfNotNull
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO
import coil.load
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates

internal class MktProductDetailsFragment : Fragment(), CustomKoinComponent {
    private var _binding: FragmentMktProductDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MktProductViewModel by viewModel()
    private val navigation: MktNavigation by inject { parametersOf(this) }
    private val dbHelper: MktDBHelper by inject()

    private var productId by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMktProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productId = arguments?.getInt("id") ?: 0
        viewModel.fetchProduct(productId)
        setupObservers()
        setupVisibility()
        setupNavbar(requireActivity())
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.product.collectIfNotNull {
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
        onError(errorType) { viewModel.fetchProduct(productId) }
    }

    private fun onProductsLoading() {
        setupVisibility(loading = true)
    }

    private fun onProductsSuccess(data: MktProductResponseVO) {
        with(binding) {
            mktDetailsPrice.text = getString(R.string.price_template, data.price)
            mktDetailsDescription.text = data.active.toString()
            mktDetailsName.text = data.name
            mktDetailsImage.apply {
                load(data.mainImage) {
                    crossfade(true)
                    placeholder(R.drawable.ic_favorite_24)
                }
                contentDescription = data.name
            }
            mktDetailsBuy.setOnClickListener {
                addToCart(data)
            }
        }
        setupVisibility(content = true)
    }

    private fun setupNavbar(activity: FragmentActivity) = with(binding) {
        mktDetailsNavbar.navbar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> navigation.popBackStack()
                R.id.action_cart -> navigation.navigateToCheckout()
                R.id.action_favorite -> navigation.navigateToFavourites(activity)
                R.id.action_settings -> navigation.navigateToSettings(activity)
                else -> viewModel.fetchProduct(productId)
            }
            true
        }
    }

    private fun setupVisibility(
        loading: Boolean = false,
        content: Boolean = false,
        error: Boolean = false
    ) =
        with(binding) {
            mktDetailsLoading.root.isVisible = loading
            mktDetailsContentGroup.isVisible = content
            mktErrorScreen.root.isVisible = error
            if (loading) {
                root.post {
                    mktDetailsLoading.root.announceForAccessibility(
                        requireContext().getString(
                            R.string.loading
                        )
                    )
                }
            }
        }

    private fun addToCart(productResponseVO: MktProductResponseVO) {
        val addResult = MktDBQueries(dbHelper).addProduct(productResponseVO)
        if (addResult.toInt() != -1) {
            Toast.makeText(context, "Adicionado ao carrinho", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(context, "Erro ao carrinho", Toast.LENGTH_SHORT).show()
    }

}