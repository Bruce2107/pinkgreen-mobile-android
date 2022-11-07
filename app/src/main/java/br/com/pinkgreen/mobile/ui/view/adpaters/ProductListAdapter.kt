package br.com.pinkgreen.mobile.ui.view.adpaters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.pinkgreen.mobile.R
import br.com.pinkgreen.mobile.databinding.ProductItemBinding
import br.com.pinkgreen.mobile.ui.vo.ProductResponseVO

class ProductListAdapter(private val products: List<ProductResponseVO>) :
    RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {
    inner class ProductListViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductResponseVO) {
//            binding.productImage = item.image
            binding.productName.text = item.name
            binding.productPrice.text =
                Resources.getSystem().getString(R.string.price_template, item.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder =
        ProductListViewHolder(
            binding = ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size
}