package com.example.tiendapersonal.datasource
import com.example.tiendapersonal.datasource.domainclasses.ProductDetails
import com.example.tiendapersonal.datasource.domainclasses.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface APIPlaceHolder {
    @GET("devices")
    fun getAllProducts(): Call<Array<Result>>

    @GET("devices/{id}")
    fun getProductDetails(@Path ("id") id:String): Call<ProductDetails?>
}