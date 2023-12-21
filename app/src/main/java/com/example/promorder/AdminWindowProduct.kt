package com.example.promorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.show()
            activity.supportActionBar?.apply {
                title = "Товары для продажи"
            }
        }
        val infoadminprod:TextView = view.findViewById(R.id.infoadminprod)
        val recyclerView: RecyclerView = view.findViewById(R.id.recProd)
        val itemDecoration = ItemOffsetDecoration(20)
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.layoutManager = LinearLayoutManager(context)
        GlobalScope.launch(Dispatchers.IO) {
            val selectProdList = db.ProductDao().selectProducts()
            withContext(Dispatchers.Main) {
                val adapter = AdapterProd(selectProdList)
                recyclerView.adapter = adapter
                if (adapter.itemCount == 0) {
                    infoadminprod.visibility = View.VISIBLE
                } else {
                    infoadminprod.visibility = View.GONE
                }
            }
        }



        val btnadd:ImageView = view.findViewById(R.id.addprod)
        btnadd.setOnClickListener{

            val newFragment: Fragment = FragProdAdd()

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