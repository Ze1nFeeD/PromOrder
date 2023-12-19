package com.example.promorder

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.ProductEntity
import com.example.promorder.room.RoomDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdapterProdUser (private val dataList: List<ProductEntity>) : RecyclerView.Adapter<AdapterProdUser.ViewHolder>() {
    private lateinit var db: RoomDb

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameprod: TextView = view.findViewById(R.id.nameproduser)
        val countprod: TextView = view.findViewById(R.id.countproduser)
        val priceprod: TextView = view.findViewById(R.id.priceproduser)
        val btnAddOrder: Button = view.findViewById(R.id.btnAddOrder)
        init {
            btnAddOrder.setOnClickListener {
                Toast.makeText(view.context, "Да работаю", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProdUser.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemrecycleproduser, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterProdUser.ViewHolder, position: Int) {
        db = DatabaseProvider.getDatabase(holder.itemView.context)

        val data = dataList[position]
        holder.nameprod.text = data.nameproduct
        holder.countprod.text = data.countproduct
        holder.priceprod.text = data.priceproduct

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
