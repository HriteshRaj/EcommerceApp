package com.example.apiproject1

import MyData
import Product
import android.app.Activity
import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(val context:Activity,val productArrayList:List<Product>):RecyclerView.Adapter<MyAdapter.MyviewHolder>() {

    private lateinit var listener:OnItemClickListener

    interface OnItemClickListener {
        fun onitemclick(position: Int)}
            fun setonItemCliclListener(mlistener:OnItemClickListener){
                listener=mlistener
            }




    class MyviewHolder(itemView: View,listener: OnItemClickListener):RecyclerView.ViewHolder(itemView) {

       init {
           itemView.setOnClickListener{
            listener.onitemclick(adapterPosition)
           }
       }

  val productimage=itemView.findViewById<ShapeableImageView>(R.id.productimage)
        val productname=itemView.findViewById<TextView>(R.id.textviewofproduct)
        val productprice=itemView.findViewById<TextView>(R.id.price)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {

        val itemview=LayoutInflater.from(context).inflate(R.layout.eachdata,parent,false)
        return MyviewHolder(itemview, listener )


    }

    override fun getItemCount(): Int {
  return productArrayList.size

    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val currentitem = productArrayList[position]
        holder.productname.text = currentitem.title

        holder.productprice.text="$" +currentitem.price.toString()



        Picasso.get().load(currentitem.thumbnail).into(holder.productimage);

    }

        // Update the adapter data with the filtered items
        // notifyDataSetChanged() or other appropriate method
        fun updateData(filteredItems: List<Product>) {
            // Replace the existing data with the filtered items
            val items= filteredItems

            // Notify the adapter that the data has changed
            notifyDataSetChanged()

    }


}