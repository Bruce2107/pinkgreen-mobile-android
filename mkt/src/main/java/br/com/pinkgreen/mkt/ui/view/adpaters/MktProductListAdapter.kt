package br.com.pinkgreen.mkt.ui.view.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pinkgreen.mkt.R
import br.com.pinkgreen.mkt.databinding.MktProductItemBinding
import br.com.pinkgreen.mkt.ui.viewstate.vo.MktProductResponseVO

internal class MktProductListAdapter(
    private val context: Context,
    private val products: List<MktProductResponseVO>
) : RecyclerView.Adapter<MktProductListAdapter.ProductListViewHolder>() {
    inner class ProductListViewHolder(private val binding: MktProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MktProductResponseVO) {
//            binding.productImage = item.image
            binding.productName.text = item.name
            binding.productPrice.text =
               context.getString(R.string.price_template, item.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder =
        ProductListViewHolder(
            binding = MktProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}