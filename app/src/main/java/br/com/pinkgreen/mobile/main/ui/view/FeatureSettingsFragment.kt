package br.com.pinkgreen.mobile.main.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.pinkgreen.mkt.init.MktFeatureInitializer
import br.com.pinkgreen.mobile.databinding.FragmentFeatureSettingsBinding
import br.com.pinkgreen.mkt.init.MktParams
import br.com.pinkgreen.mobile.main.MockApi
import br.com.pinkgreen.mobile.main.MockApiResponse
import br.com.pinkgreen.mobile.lib.PinkgreenApiImpl
import br.com.pinkgreen.mobile.lib.RetrofitClient
import br.com.pinkgreen.mobile.main.ui.view.adapters.PinkgreenApiOptionsAdapter
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchProductOption
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchProductsOption
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchSkuCodeOption
import br.com.pinkgreen.mobile.utils.Utils
import retrofit2.Response

class FeatureSettingsFragment : Fragment() {
    private var _binding: FragmentFeatureSettingsBinding? = null
    private val binding get() = _binding!!
    private var mode: Int = 0
    private var fetchProductsOption = PinkgreenFetchProductsOption(
        title = "fetchProducts",
        response = MockApiResponse.MktProducts.fetchProducts
    )

    private var fetchProductOption = PinkgreenFetchProductOption(
        title = "fetchProduct",
        response = MockApiResponse.MktProducts.fetchProduct
    )

    private var fetchSkuCodeOption = PinkgreenFetchSkuCodeOption(
        title = "fetchSkuCode",
        response = MockApiResponse.MktProducts.fetchSkuCode
    )


    private val apiOptions by lazy {
        listOf(
            fetchProductsOption,
            fetchProductOption,
            fetchSkuCodeOption
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeatureSettingsBinding.inflate(layoutInflater, container, false)
        setupViews()
        mode = arguments?.getInt("mode") ?: 0
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        updateMock() // permite erro
    }

    private fun setupViews() {
        binding.internshipApiOptions.adapter =
            PinkgreenApiOptionsAdapter(requireActivity().application, apiOptions)

        binding.internshipButton.setOnClickListener {
            startFeature()
        }
    }

    private fun updateMock() {
        fetchProductsOption = PinkgreenFetchProductsOption(
            title = "fetchProducts",
            response = Response.success(
                Utils.getJsonDataFromAsset(
                    requireActivity().application,
                    "products.json"
                )
            )
        )

        fetchProductOption = PinkgreenFetchProductOption(
            title = "fetchProduct",
            response = Response.success(
                Utils.getJsonDataFromAsset(
                    requireActivity().application,
                    "product.json"
                )
            )
        )
        fetchSkuCodeOption = PinkgreenFetchSkuCodeOption(
            title = "fetchSkuCode",
            response = Response.success(
                Utils.getJsonDataFromAsset(
                    requireActivity().application,
                    "sku.json"
                )
            )
        )
    }

    private fun startFeature() {
        MktFeatureInitializer.close()
        val apiLib = RetrofitClient.getInstance().create(PinkgreenApiImpl::class.java)

        val apiMock = MockApi(
            fetchProducts = fetchProductsOption,
            fetchProduct = fetchProductOption,
            fetchSkuCode = fetchSkuCodeOption
        )
        val api = when (mode) {
            1 -> apiMock
            else -> apiLib
        }
        MktFeatureInitializer.init(
            MktParams(
                requireActivity().application,
                api,
            )
        )
        requireContext().startInternshipFeature()
    }
}