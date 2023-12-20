package com.example.promorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserWindowProduct : Fragment() {
    private lateinit var db: RoomDb
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_user_window_product, container, false)

        db = DatabaseProvider.getDatabase(requireContext())
        val infoUserprod:TextView = view.findViewById(R.id.infoUserprod)
        val recyclerView: RecyclerView = view.findViewById(R.id.recProdUser)
        recyclerView.layoutManager = LinearLayoutManager(context)
        GlobalScope.launch(Dispatchers.IO) {
            val selectProdList = db.ProductDao().selectProducts()
            withContext(Dispatchers.Main) {
                val adapter = AdapterProdUser(selectProdList)
                recyclerView.adapter = adapter
                if (adapter.itemCount == 0) {
                    infoUserprod.visibility = View.VISIBLE
                } else {
                    infoUserprod.visibility = View.GONE
                }
            }
        }

        return view
    }

}