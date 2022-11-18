package br.com.pinkgreen.mkt.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.pinkgreen.mkt.R
import br.com.pinkgreen.mkt.databinding.FragmentMktProductDetailsBinding
import br.com.pinkgreen.mkt.di.CustomKoinComponent

class MktProductDetailsFragment : Fragment(), CustomKoinComponent {
    private var _binding: FragmentMktProductDetailsBinding? = null
    private val binding get() = _binding!!

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

        setupView()
    }

    private fun setupView() = with(binding) {
        mktProductDetailsName.text = "Mesa de centro"
        mktProductDetailsPrice.text = getString(R.string.price_template, 1022.2)
        mktProductDetailsBtnBuy.apply {
            text = "comprar"
        }

    }
}