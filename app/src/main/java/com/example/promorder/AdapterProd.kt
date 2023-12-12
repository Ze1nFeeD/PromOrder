package com.example.promorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.ProductEntity
import com.example.promorder.room.UserEntity

class AdapterProd(private val dataList: List<ProductEntity>) : RecyclerView.Adapter<AdapterProd.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameprod: TextView = view.findViewById(R.id.nameprod)
        val countprod: TextView = view.findViewById(R.id.countprod)
        val priceprod: TextView = view.findViewById(R.id.priceprod)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProd.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemrecycleprod, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterProd.ViewHolder, position: Int) {
        val data = dataList[position]
        holder.nameprod.text = data.nameproduct
        holder.countprod.text = data.countproduct
        holder.priceprod.text = data.priceproduct
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
