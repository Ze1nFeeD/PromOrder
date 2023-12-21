package com.example.promorder

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.hide()
        }
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
                var isValid = true


                if (nameproduct.text.toString().isBlank()) {
                    nameproduct.setBackgroundResource(R.drawable.bg_red_edittext)
                    Toast.makeText(context,"Поле 'Название продукта' не может быть пустым",Toast.LENGTH_SHORT).show()
                    isValid = false
                } else {
                    nameproduct.setBackgroundResource(R.drawable.bg)
                }


                if (countproduct.text.toString().isBlank() || countproduct.text.toString().toInt() < 0) {
                    countproduct.setBackgroundResource(R.drawable.bg_red_edittext)
                    Toast.makeText(context,"Поле 'Количество' не может быть пустым или меньше 1",Toast.LENGTH_SHORT).show()
                    isValid = false
                } else {
                    countproduct.setBackgroundResource(R.drawable.bg)
                }


                if (priceproduct.text.toString().isBlank() || priceproduct.text.toString().toInt() < 0) {
                    priceproduct.setBackgroundResource(R.drawable.bg_red_edittext)
                    Toast.makeText(context,"Поле 'Стоимость' не может быть пустым или меньше 1",Toast.LENGTH_SHORT).show()
                    isValid = false
                } else {
                    priceproduct.setBackgroundResource(R.drawable.bg)
                }

                if (isValid) {
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
                } else {
                    Toast.makeText(context,"Ошибка",Toast.LENGTH_SHORT).show()
                }

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
