package com.example.promorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AdminWindowProduct : Fragment() {
    private lateinit var db: RoomDb

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_window_product, container, false)
        db = DatabaseProvider.getDatabase(requireContext())

        val recyclerView: RecyclerView = view.findViewById(R.id.recProd)
        recyclerView.layoutManager = LinearLayoutManager(context)
        GlobalScope.launch(Dispatchers.IO) {
            val selectProdList = db.ProductDao().selectProducts()
            withContext(Dispatchers.Main) {
                val adapter = AdapterProd(selectProdList)
                recyclerView.adapter = adapter
            }
        }



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