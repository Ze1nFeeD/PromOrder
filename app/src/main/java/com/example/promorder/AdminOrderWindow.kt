package com.example.promorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AdminOrderWindow : Fragment() {
    private lateinit var db: RoomDb
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_order_window, container, false)

         db = DatabaseProvider.getDatabase(requireContext())

         val recyclerView: RecyclerView = view.findViewById(R.id.recOrderAdmin)
         recyclerView.layoutManager = LinearLayoutManager(context)
         GlobalScope.launch(Dispatchers.IO) {
             val selectCurOrdList = db.orderDao().selectCurOrd()
             withContext(Dispatchers.Main) {
                 val adapter = AdapterOrderAdmin(selectCurOrdList)
                 recyclerView.adapter = adapter
             }
         }
         return view
    }

}