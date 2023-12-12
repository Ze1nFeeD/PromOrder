package com.example.promorder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.ProductEntity
import com.example.promorder.room.RoomDb
import com.example.promorder.room.UserEntity
import kotlinx.coroutines.launch

class FragProdAdd : Fragment() {
    private lateinit var db: RoomDb

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_frag_prod_add, container, false)
        db = DatabaseProvider.getDatabase(requireContext())
        val btnaddprod: Button = view.findViewById(R.id.btnAddProd)

        val nameproduct: EditText = view.findViewById(R.id.nameprodadd)
        val countproduct: EditText = view.findViewById(R.id.countprodadd)
        val priceproduct: EditText = view.findViewById(R.id.priceprodadd)
        btnaddprod.setOnClickListener {
            lifecycleScope.launch {
                db.ProductDao().insertProduct(
                    ProductEntity(
                        nameproduct = nameproduct.text.toString(),
                        countproduct = countproduct.text.toString(),
                        priceproduct = priceproduct.text.toString()
                    )
                )
            }

            val newFragment: Fragment = AdminWindowProduct()

            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(
                com.example.promorder.R.id.container,
                newFragment
            )

            transaction.commit()
        }
        val btncanadd:Button = view.findViewById(R.id.btncanadd)
        btncanadd.setOnClickListener {
            val newFragment: Fragment = AdminWindowProduct()

            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(
                com.example.promorder.R.id.container,
                newFragment
            )

            transaction.commit()
        }
        return view
    }
}
