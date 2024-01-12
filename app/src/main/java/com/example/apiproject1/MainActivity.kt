package com.example.apiproject1

import MyData
import Product
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ActionMenuView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiproject1.databinding.ActivityMainBinding
import com.google.gson.internal.bind.ArrayTypeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import java.util.Locale

class MainActivity : AppCompatActivity() {


    private lateinit var myAdapter: MyAdapter

    private lateinit var recyclerview:RecyclerView

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var searchview=findViewById<SearchView>(R.id.searchview)

        var searchlist: ArrayList<Product> = ArrayList()


     recyclerview= findViewById(R.id.recyclerview)


        val retrofitBuilder= Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        //lets get the data

        val retrofitData= retrofitBuilder.getProductData()   //getproductdata is the interface
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                 val responsebody= response.body()
                val productList=responsebody?.products!!
                val dataString= StringBuilder()
                for(mydata in productList){
                    dataString.append(mydata.title)


                }


                searchview.clearFocus()
                searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        searchview.clearFocus()
                        return true
                    }

                    override fun onQueryTextChange(newtext: String?): Boolean {
                        searchlist.clear()
                        val searchtext=newtext!!.lowercase(Locale.getDefault())
                        if(searchtext.isNotEmpty()){
                            productList.forEach {
                                if(it.title.toLowerCase(Locale.getDefault()).contains(searchtext)) {
                                    searchlist.add(it)

                                }
                                recyclerview.adapter!!.notifyDataSetChanged()
                            }
                        }else{
                            searchlist.clear()
                            searchlist.addAll(productList)
                            recyclerview.adapter!!.notifyDataSetChanged()


                        }
                        return false


                    }

                })


            searchlist.addAll(productList)
             myAdapter= MyAdapter(this@MainActivity,searchlist)
                recyclerview.adapter=myAdapter
                recyclerview.layoutManager=LinearLayoutManager(this@MainActivity)

                myAdapter.setonItemCliclListener(object: MyAdapter.OnItemClickListener{



                    override fun onitemclick(position: Int) {
                        val intent= Intent(this@MainActivity,secondActivity::class.java)
                        intent.putExtra("title",productList[position].title)
                        intent.putExtra("image",productList[position].thumbnail)
                        intent.putExtra("price",productList[position].price)
                        intent.putExtra("rating",productList[position].rating)
                        intent.putExtra("description",productList[position].description)
                        startActivity(intent)

                    }

                })

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
               Log.d("Main Activity FAILING","On failure"+t.message)
            }
        })

    }
}