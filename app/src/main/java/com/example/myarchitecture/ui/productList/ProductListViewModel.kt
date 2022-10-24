package com.example.myarchitecture.ui.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myarchitecture.DataWrapper
import com.example.myarchitecture.Failure
import com.example.myarchitecture.Success
import com.example.myarchitecture.domain.TotalProductWithDiscount
import com.example.myarchitecture.repository.ProductRepositoryImpl
import com.example.myarchitecture.utils.CurrencyFormater
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productRepository: ProductRepositoryImpl) : ViewModel() {

    private val _productDiscountLiveData: MutableLiveData<DataWrapper<ProductDiscountDataVM>> = MutableLiveData()
    val productDiscountLiveData: LiveData<DataWrapper<ProductDiscountDataVM>> = _productDiscountLiveData


    fun combineProductListAndDiscountList() {
        return runBlocking {
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    val productWithDiscount = productRepository.getProductWithDiscount()
                    val productDiscountDataVM = map(productWithDiscount)
                    _productDiscountLiveData.postValue(Success(productDiscountDataVM))
                }
            } catch (e: java.lang.Exception) {
                _productDiscountLiveData.postValue(Failure(e))
                e.printStackTrace()
                throw  e
            }
        }
    }


    data class ProductDiscountDataVM(val totalAmount: String, val items: List<ProductDiscountItemVM>)
    data class ProductDiscountItemVM(
        val productId: String,
        val productName: String,
        val discountDescription: String?,
        val price: BigDecimal,
        val priceDiscounted: BigDecimal?
    )

    private fun map(totalProductWithDiscount: TotalProductWithDiscount): ProductDiscountDataVM {
        val priceTotalString = CurrencyFormater.format(totalProductWithDiscount.totalAmount).orEmpty()
        val productDiscountItemList = arrayListOf<ProductDiscountItemVM>()

        totalProductWithDiscount.productWithDiscountList.map {
            productDiscountItemList.add(
                ProductDiscountItemVM(
                    it.productId,
                    it.name,
                    it.discount?.description,
                    it.price,
                    if (it.discount != null) it.price.minus(it.discount.discountAmount) else null
                )
            )
        }
        return ProductDiscountDataVM(priceTotalString, productDiscountItemList)
    }
}