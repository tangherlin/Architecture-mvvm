package com.example.myarchitecture.ui.productList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myarchitecture.databinding.ItemProductBinding
import com.example.myarchitecture.utils.CurrencyFormater

class ProductAdapter() :
    ListAdapter<ProductListViewModel.ProductDiscountItemVM, ProductAdapter.ViewHolder>(ProductListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductListViewModel.ProductDiscountItemVM) {
            binding.tvName.text = item.productName
            binding.textPrice.text = CurrencyFormater.format(item.price)

            if (item.priceDiscounted != null) {
                binding.viewCrossPrice.isVisible = true
                binding.textDiscountPrice.text = CurrencyFormater.format(item.priceDiscounted)
                item.discountDescription?.let { binding.textDiscountName.text = it }
            } else {
                binding.viewCrossPrice.isVisible = false
            }
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    class ProductListDiffCallback : DiffUtil.ItemCallback<ProductListViewModel.ProductDiscountItemVM>() {

        override fun areItemsTheSame(
            oldItem: ProductListViewModel.ProductDiscountItemVM,
            newItem: ProductListViewModel.ProductDiscountItemVM
        ): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(
            oldItem: ProductListViewModel.ProductDiscountItemVM,
            newItem: ProductListViewModel.ProductDiscountItemVM
        ): Boolean {
            return oldItem == newItem
        }
    }
}