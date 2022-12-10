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
import br.com.pinkgreen.mkt.database.MktDBQueries
import br.com.pinkgreen.mkt.databinding.FragmentMktCheckoutBinding
import br.com.pinkgreen.mkt.di.CustomKoinComponent
import br.com.pinkgreen.mkt.ui.components.error.ErrorLauncherParams
import br.com.pinkgreen.mkt.ui.view.adpaters.MktCheckoutAdapter
import br.com.pinkgreen.mkt.ui.view.navigation.MktNavigation
import br.com.pinkgreen.mkt.ui.viewmodel.MktCheckoutViewModel
import br.com.pinkgreen.mkt.ui.viewstate.ErrorType
import br.com.pinkgreen.mkt.ui.viewstate.ViewState
import br.com.pinkgreen.mkt.ui.viewstate.collectIfNotNull
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO
import br.com.pinkgreen.mkt.ui.vo.MktProductsResponseVO
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.ref.WeakReference
import kotlin.properties.Delegates

internal class MktCheckoutFragment : Fragment(), CustomKoinComponent {
    private var _binding: FragmentMktCheckoutBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MktCheckoutViewModel by viewModel()
    private val navigation: MktNavigation by inject { parametersOf(this) }
    private val dbQueries: MktDBQueries by inject()

    private var totalCost by Delegates.notNull<Double>()

    private var cart: MutableList<MktProductResponseVO> = mutableListOf()
    private val adapter =
        MktCheckoutAdapter(this, cart, MktCheckoutAdapter.OnClickListener { item, pos ->
            removeProduct(item, pos)
        }, MktCheckoutAdapter.OnClickListener { item, _ ->
            navigation.navigateFromCheckoutToDetails(item.id)
        })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMktCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchProducts()
        setupObservers()
        setupVisibility()
        setupNavbar(requireActivity())
//        setupSwipe()
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
        onError(errorType) { }
    }

    private fun onProductsLoading() {
        setupVisibility(loading = true)
    }

    private fun onProductsSuccess(data: MktProductsResponseVO) {
        with(binding) {
            for (product in data.products) {
                cart.add(product)
            }

            updateTotal(cart)
            mktCheckoutList.adapter = adapter
//            mktCheckoutList.adapter = MktCheckoutAdapter(
//                fragment = this@MktCheckoutFragment,
//                products = cart,
//                onClickListener = MktCheckoutAdapter.OnClickListener { item, pos ->
//                    removeProduct(item, pos)
//                },
//                onCardClickListener = MktCheckoutAdapter.OnClickListener { item, _ ->
//                    navigation.navigateFromCheckoutToDetails(item.id)
//                }
//            )
            mktCheckoutBuy.setOnClickListener {
                buyAction()
            }
        }
        setupVisibility(content = true)
    }

    private fun setupNavbar(activity: FragmentActivity) = with(binding) {
        mktCheckoutNavbar.navbar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> navigation.navigateToHome()
                R.id.action_cart -> {}
                R.id.action_favorite -> navigation.navigateToFavourites()
                R.id.action_settings -> navigation.navigateToSettings(activity)
            }
            true
        }
    }

    private fun setupVisibility(loading: Boolean = false, content: Boolean = false) =
        with(binding) {
            mktCheckoutLoading.root.isVisible = loading
            mktCheckoutContentGroup.isVisible = content
            if (loading) {
                root.post {
                    mktCheckoutLoading.root.announceForAccessibility(
                        requireContext().getString(
                            R.string.loading
                        )
                    )
                }
            }
        }

    private fun updateTotal(list: List<MktProductResponseVO>) {
        totalCost = list.fold(0.0) { acc, item -> acc + item.price }
        binding.mktCheckoutTotal.text = getString(R.string.price_template, totalCost)
    }

//    private fun setupSwipe() {
//        val itemTouchHelper = object : ItemTouchHelper.Callback() {
//            override fun getMovementFlags(
//                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder
//            ): Int {
//                return makeMovementFlags(
//                    ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.START or ItemTouchHelper.END
//                )
//            }
//
//            override fun onMove(
//                recyclerView: RecyclerView,
//                viewHolder: RecyclerView.ViewHolder,
//                target: RecyclerView.ViewHolder
//            ): Boolean {
//                return false
//            }
//
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                removeProduct(viewHolder)
//            }
//        }
//        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.mktCheckoutList)
//    }

    private fun removeProduct(productResponseVO: MktProductResponseVO, pos: Int) {
        dbQueries.deleteProduct(productResponseVO.id)
        cart.removeAt(pos)
        updateTotal(cart)
    }

//    private fun removeProduct(viewHolder: RecyclerView.ViewHolder) {
//        val product = cart[viewHolder.adapterPosition]
//        dbQueries.deleteProduct(product.id)
//        cart.removeAt(viewHolder.adapterPosition)
//        adapter.notifyItemRemoved(viewHolder.adapterPosition)
//        Toast.makeText(requireContext(), "Removido", Toast.LENGTH_SHORT).show()
//    }

    private fun buyAction() {
        dbQueries.clearCart()
        cart.clear()
        updateTotal(cart)
        adapter.notifyDataSetChanged()
        Toast.makeText(context, "Compra realizada", Toast.LENGTH_SHORT).show()
    }
}