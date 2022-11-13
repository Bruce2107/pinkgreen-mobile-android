package br.com.pinkgreen.mobile.main.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.pinkgreen.mobile.R
import br.com.pinkgreen.mobile.databinding.FragmentFeaturePresentationBinding


class FeaturePresentationFragment : Fragment() {
    private var _binding: FragmentFeaturePresentationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeaturePresentationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareMockButton()
        prepareLibButton()
    }

    private fun prepareMockButton() {
        binding.mockButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_featurePresentationFragment_to_featureSettingsFragment,
                bundleOf("mode" to 1)
            )
        }
    }

    private fun prepareLibButton() {
        binding.libButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_featurePresentationFragment_to_featureSettingsFragment,
                bundleOf("mode" to 0)
            )
        }
    }
}