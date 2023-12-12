package com.example.promorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction


class AdminWindowProduct : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_window_product, container, false)




        val btnadd:ImageView = view.findViewById(R.id.addprod)
        btnadd.setOnClickListener{

            val newFragment: Fragment = FragProdAdd() //YourFragment заменить на нужный

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