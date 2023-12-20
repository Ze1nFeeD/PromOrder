package com.example.promorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.UserEntity

class AdapterUser (private val userList: List<UserEntity>) : RecyclerView.Adapter<AdapterUser.EnterpriseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnterpriseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemrecycleclient, parent, false)
        return EnterpriseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: EnterpriseViewHolder, position: Int) {
        val enterprise = userList[position]
        holder.bind(enterprise)
    }

    inner class EnterpriseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameorg)
        private val innTextView: TextView = itemView.findViewById(R.id.inn)
        private val ogrnTextView: TextView = itemView.findViewById(R.id.ogrn)

        fun bind(enterprise: UserEntity) {
            nameTextView.text = enterprise.name
            innTextView.text = enterprise.inn
            ogrnTextView.text = enterprise.ogrn
        }
    }
}