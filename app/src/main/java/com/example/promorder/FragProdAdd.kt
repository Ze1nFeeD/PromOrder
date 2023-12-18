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
import android.widget.Toast
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

        var editeModeProd = false
        val arguments = arguments
        val nameproduct: EditText = view.findViewById(R.id.nameprodadd)
        val countproduct: EditText = view.findViewById(R.id.countprodadd)
        val priceproduct: EditText = view.findViewById(R.id.priceprodadd)

        if (arguments != null) {
            nameproduct.setText(arguments.getString("nameprod"))
            countproduct.setText(arguments.getString("countprod"))
            priceproduct.setText(arguments.getString("priceprod"))
            editeModeProd = true
        }

        btnaddprod.setOnClickListener {
            try {


                lifecycleScope.launch {
                    if (editeModeProd) {
                        db.ProductDao().updateProd(
                            nameproduct = nameproduct.text.toString(),
                            countprod = countproduct.text.toString(),
                            priceprod = priceproduct.text.toString(),
                            idprod = arguments!!.getInt("idprod")
                        )
                    } else {
                        db.ProductDao().insertProduct(
                            ProductEntity(
                                nameproduct = nameproduct.text.toString(),
                                countproduct = countproduct.text.toString(),
                                priceproduct = priceproduct.text.toString()
                            )
                        )
                    }
                }
                val newFragment: Fragment = AdminWindowProduct()

                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                transaction.replace(
                    com.example.promorder.R.id.container,
                    newFragment
                )

                transaction.commit()
            }
            catch (e: Exception)
            {

            }
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
