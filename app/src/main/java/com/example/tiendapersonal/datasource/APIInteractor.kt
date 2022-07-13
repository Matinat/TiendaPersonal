package com.example.tiendapersonal.datasource

import com.example.tiendapersonal.common.domainclasses.Card
import com.example.tiendapersonal.datasource.domainclasses.ProductDetails
import com.example.tiendapersonal.datasource.domainclasses.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIInteractor {
    private var placeHolder: APIPlaceHolder
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://61967289af46280017e7e0c0.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    init {
        placeHolder = retrofit.create(APIPlaceHolder::class.java)
    }

    /**
     * obtiene una lista de productos
     */
    fun getAllProducts(callback: (ArrayList<Card>) -> Unit) {
        val call: Call<Array<Result>> = placeHolder.getAllProducts()
        call.enqueue(object : Callback<Array<Result>> {
            override fun onResponse(call: Call<Array<Result>>, response: Response<Array<Result>>) {
                if (response.isSuccessful) {
                    val products = ArrayList<Card>()
                    for (result in response.body()!!) {
                        products.add(Card(result))
                    }
                    callback(products)
                }
            }
            override fun onFailure(call: Call<Array<Result>>, t: Throwable) {
                callback(ArrayList())
            }
        })
    }

    /**
     * obtiene detalles de producto por ID
     */
    fun getProductDetails(itemId: String, callback: (ProductDetails?) -> Unit) {
        val call: Call<ProductDetails?> = placeHolder.getProductDetails(itemId)
        call.enqueue(object : Callback<ProductDetails?> {
            override fun onResponse(call: Call<ProductDetails?>, response: Response<ProductDetails?>) {
                if (response.isSuccessful) {
                    callback(response.body()!! )
                }
            }
            override fun onFailure(call: Call<ProductDetails?>, t: Throwable) {
                callback(null)
            }
        })
    }

}