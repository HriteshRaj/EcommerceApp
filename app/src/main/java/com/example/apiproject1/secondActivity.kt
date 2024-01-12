package com.example.apiproject1

import Product
import android.graphics.drawable.InsetDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class secondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)



       val title= intent.getStringExtra("title")

        val price=intent.getIntExtra("price",0)
        val img=intent.getStringExtra("image")
        val rting=intent.getDoubleExtra("rating",0.0)
        val desc=intent.getStringExtra("description")


        val tt=findViewById<TextView>(R.id.producttitlle)
        val pimg= findViewById<ImageView>(R.id.productimmm)
        val prprice=findViewById<TextView>(R.id.productpricdse)

        val descript=findViewById<TextView>(R.id.productdescription)


        val rt=findViewById<TextView>(R.id.productrating)
        tt.text="Product Name: \n"+ title
        Picasso.get().load(img).into(pimg)

        rt.text="RATING : \n"+rting.toString()
        descript.text= "DESCRIPTION : \n"+ desc.toString()
        prprice.text= "PRICE :\n$"+ price.toString()

    }
}
