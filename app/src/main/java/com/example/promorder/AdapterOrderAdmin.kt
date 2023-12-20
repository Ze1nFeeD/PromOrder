package com.example.promorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.OrderEntity
import com.example.promorder.room.RoomDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdapterOrderAdmin (private val orders: List<OrderEntity>) : RecyclerView.Adapter<AdapterOrderAdmin.OrderViewHolder>() {
    private lateinit var db: RoomDb

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemrecycleorderamdin, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        db = DatabaseProvider.getDatabase(holder.itemView.context)
        val order = orders[position]
        holder.bind(order)

        holder.nameTextView.text = order.nameproduct
        holder.countTextView.text = order.countproduct
        holder.priceTextView.text = order.priceorder

        holder.acceptButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.orderDao().updateOrd(
                    status = "Принят",
                    idord = order.id
                )

                val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val newFragment = AdminOrderWindow()

                fragmentTransaction.replace(R.id.container, newFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()

            }
        }
        holder.declineButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.orderDao().updateOrd(
                    status = "Отказ",
                    idord = order.id
                )
                val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                val newFragment = AdminOrderWindow()

                fragmentTransaction.replace(R.id.container, newFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         val nameTextView: TextView = itemView.findViewById(R.id.nameordadmin)
         val countTextView: TextView = itemView.findViewById(R.id.countordadmin)
         val priceTextView: TextView = itemView.findViewById(R.id.priceordadmin)
         val acceptButton: Button = itemView.findViewById(R.id.acceptbtn)
         val declineButton: Button = itemView.findViewById(R.id.cancelbtn)

        fun bind(order: OrderEntity) {
            val order = orders[position]

            nameTextView.text = order.nameproduct
            countTextView.text = order.countproduct
            priceTextView.text = order.priceorder

        }
    }
}