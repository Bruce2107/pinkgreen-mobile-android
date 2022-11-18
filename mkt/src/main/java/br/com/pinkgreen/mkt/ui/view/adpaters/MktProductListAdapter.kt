package br.com.pinkgreen.mkt.ui.view.adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.pinkgreen.mkt.R
import br.com.pinkgreen.mkt.databinding.MktProductItemBinding
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO

internal class MktProductListAdapter(
    private val fragment: Fragment,
    private val products: List<MktProductResponseVO>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MktProductListAdapter.ProductListViewHolder>() {
    inner class ProductListViewHolder(private val binding: MktProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MktProductResponseVO) {
//            binding.productImage = item.image
            binding.productName.text = item.name
            binding.productPrice.text =
                fragment.getString(R.string.price_template, item.price)
            binding.productFastBuy.setOnClickListener(onClickListener)
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