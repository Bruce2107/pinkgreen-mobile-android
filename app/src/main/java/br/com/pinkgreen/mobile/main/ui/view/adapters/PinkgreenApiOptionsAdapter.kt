package br.com.pinkgreen.mobile.main.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pinkgreen.mkt.data.dto.MktProductsResponseDTO
import br.com.pinkgreen.mobile.R
import br.com.pinkgreen.mobile.databinding.PinkgreenApiOptionItemBinding
import br.com.pinkgreen.mobile.main.MockApiResponse
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenApiOption
import br.com.pinkgreen.mobile.main.ui.vo.PinkgreenFetchProductsOption
import retrofit2.Response

class PinkgreenApiOptionsAdapter(private val list: List<PinkgreenApiOption>) :
    RecyclerView.Adapter<PinkgreenApiOptionsAdapter.InternshipApiOptionsViewHolder>() {
    inner class InternshipApiOptionsViewHolder(private val binding: PinkgreenApiOptionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PinkgreenApiOption) {
            binding.title.text = item.title

            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.radio_button_success -> {
                        when (item) {
                            is PinkgreenFetchProductsOption -> item.response =
                                MockApiResponse.MktProducts.fetchProducts
                            else -> {}
                        }
                    }
                    R.id.radio_button_error -> {
                        when (item) {
                            is PinkgreenFetchProductsOption -> item.response =
                                MockApiResponse.error as Response<MktProductsResponseDTO>
                            else -> {}
                        }
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): InternshipApiOptionsViewHolder =
        InternshipApiOptionsViewHolder(
            binding = PinkgreenApiOptionItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: InternshipApiOptionsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}