package com.example.tiendapersonal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tiendapersonal.common.domainclasses.Card
import com.example.tiendapersonal.datasource.APIInteractor
import com.example.tiendapersonal.datasource.domainclasses.ProductDetails
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mlInteractor: APIInteractor by lazy {
        APIInteractor()
    }

    private val items: MutableLiveData<ArrayList<Card>> by lazy {
        MutableLiveData<ArrayList<Card>>().also {
            loadAllProducts()
        }
    }

    private val productDetails: MutableLiveData<ProductDetails?> by lazy {
        MutableLiveData<ProductDetails?>()
    }

    fun getItems(): LiveData<ArrayList<Card>> {
        return items
    }

    fun getProductDetailsLiveData(): LiveData<ProductDetails?> {
        return productDetails
    }

    fun getProductDetails(itemId: String) {
        mlInteractor.getProductDetails(itemId) { details ->
            productDetails.value = details
        }
    }

    private fun loadAllProducts() {
        mlInteractor.getAllProducts { product ->
            items.value = product
        }
    }
}



