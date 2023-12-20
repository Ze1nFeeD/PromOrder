package com.example.promorder

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.ProductEntity
import com.example.promorder.room.RoomDb
import com.example.promorder.room.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdapterProd(private val dataList: List<ProductEntity>) : RecyclerView.Adapter<AdapterProd.ViewHolder>() {
    private lateinit var db: RoomDb

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
        db = DatabaseProvider.getDatabase(holder.itemView.context)

        val data = dataList[position]
        holder.nameprod.text = data.nameproduct
        holder.countprod.text = data.countproduct
        holder.priceprod.text = data.priceproduct

        holder.itemView.setOnClickListener {
            val item = dataList[position]
            val alertDialog = AlertDialog.Builder(holder.itemView.context)
                .setTitle("Выберите действие")
                .setPositiveButton("Изменить") { dialog, which ->
                    val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    val newFragment = FragProdAdd()


                    val args = Bundle()

                    args.putInt("idprod", item.id)
                    args.putString("nameprod", item.nameproduct.toString())
                    args.putString("countprod", item.countproduct.toString())
                    args.putString("priceprod", item.priceproduct.toString())

                    newFragment.arguments = args

                    fragmentTransaction.replace(R.id.container, newFragment)
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
                .setNegativeButton("Удалить") { dialog, which ->
                    // Код для обработки действия "Удалить"


                    CoroutineScope(Dispatchers.IO).launch {
                        db.ProductDao().deleteProd(item.id.toString())

                        val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        val newFragment = AdminWindowProduct()
                        fragmentTransaction.replace(R.id.container, newFragment)
                        fragmentTransaction.addToBackStack(null)
                        fragmentTransaction.commit()
                    }
                }
                .setNeutralButton("Отмена") { dialog, which ->
                    // Код для обработки действия "Отмена"
                }
                .create()

            alertDialog.show()
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
