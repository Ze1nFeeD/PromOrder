package com.example.promorder

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.OrderEntity
import com.example.promorder.room.RoomDb
import com.example.promorder.room.UserEntity
import kotlinx.coroutines.launch


class UserAddOrder : Fragment() {
    private lateinit var db: RoomDb
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_add_order, container, false)
        db = DatabaseProvider.getDatabase(requireContext())

        val arguments = arguments

        val newprodcount: EditText = view.findViewById(R.id.newprodcount)
        newprodcount.setText("1")
        val btnaddNewOrd: Button = view.findViewById(R.id.btnAddNewOrder)
        val nameordnew: TextView = view.findViewById(R.id.nameordnew)
        val newordprice: TextView = view.findViewById(R.id.newordprice)
        val finalpriceord: TextView = view.findViewById(R.id.finalpriceord)
        val statusorder = "В обработке"

        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "") ?: ""

        if (arguments != null) {
            nameordnew.setText(arguments.getString("nameprod"))
            newordprice.setText(arguments.getString("priceprod"))
            finalpriceord.setText(arguments.getString("priceprod"))
        }

        newprodcount.addTextChangedListener {
            val count =  newprodcount.text.toString().toInt()
            val price = newordprice.text.toString().toInt()
            val result = count * price
            finalpriceord.setText(result.toString())
        }
        btnaddNewOrd.setOnClickListener {
            lifecycleScope.launch {
                    db.orderDao().insertOrder(
                        OrderEntity(
                            nameproduct = nameordnew.text.toString(),
                            countproduct = newprodcount.text.toString(),
                            priceorder = finalpriceord.text.toString(),
                            iduserord = username.toString(),
                            statusorder = statusorder.toString()
                        )
                    )
            }
            val newFragment: Fragment = AdminWindowProduct()

            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(
                com.example.promorder.R.id.containerUser,
                newFragment
            )

            transaction.commit()
        }
        return view
    }

}