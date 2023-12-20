package com.example.promorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.OrderEntity
import com.example.promorder.room.RoomDb

class AdapterOrdUser (private val orders: List<OrderEntity>) : RecyclerView.Adapter<AdapterOrdUser.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemrecycleorduser, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameorduser)
        private val countTextView: TextView = itemView.findViewById(R.id.countorduser)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceorduser)
        private val statusTextView: TextView = itemView.findViewById(R.id.statusord)

        fun bind(order: OrderEntity) {
            nameTextView.text = order.nameproduct
            countTextView.text = order.countproduct
            priceTextView.text = order.priceorder
            statusTextView.text = order.statusorder
        }
    }
}