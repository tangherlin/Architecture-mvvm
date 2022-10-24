package com.example.myarchitecture.ui.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myarchitecture.Success
import com.example.myarchitecture.databinding.FragmentProductListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class ProductListFragment : Fragment() {
    private val viewModel: ProductListViewModel by viewModels()

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runBlocking {
            viewModel.combineProductListAndDiscountList()
        }
        viewModel.productDiscountLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    context?.let { ctx ->
                        binding.recyclerView.layoutManager = LinearLayoutManager(ctx)
                        val adapter = ProductAdapter()
                        binding.recyclerView.adapter = adapter
                        adapter.submitList(it.data.items)
                        binding.tvTotalPrice.text = it.data.totalAmount
                        binding.recyclerView.addItemDecoration(
                            DividerItemDecoration(
                                ctx,
                                DividerItemDecoration.VERTICAL
                            )
                        )
                    }

                }
                else -> {}
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

}