package br.com.pinkgreen.mkt.ui.view.adpaters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.pinkgreen.mkt.R
import br.com.pinkgreen.mkt.databinding.MktProductItemBinding
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO
import coil.load

internal class MktCheckoutAdapter(
    private val fragment: Fragment,
    private val products: List<MktProductResponseVO>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<MktCheckoutAdapter.ProductListViewHolder>() {
    inner class ProductListViewHolder(private val binding: MktProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MktProductResponseVO) {
            binding.productImage.apply {
                load(item.mainImage) {
                    crossfade(true)
                    placeholder(R.drawable.ic_favorite_24)
                }
                contentDescription = item.name
            }
            binding.productName.text = item.name
            binding.productPrice.text =
                fragment.getString(R.string.price_template, item.price)
            binding.productRemoveBtn.setOnClickListener {
                onClickListener.onClick(item)
            }

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

    class OnClickListener(val clickListener: (item: MktProductResponseVO) -> Unit) {
        fun onClick(item: MktProductResponseVO) = clickListener(item)
    }
}