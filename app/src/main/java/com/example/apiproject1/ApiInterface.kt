package com.example.apiproject1

import MyData
import Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

     @GET("products")                     //last point of the url of api website
    fun getProductData(): Call<MyData>

    @GET("products")
    fun gettitleData (@Query("title")productitle:String):Call<MyData>


}