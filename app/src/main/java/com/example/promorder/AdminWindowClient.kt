package com.example.promorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdminWindowClient : Fragment() {
    private lateinit var db: RoomDb

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_admin_window_client, container, false)

        db = DatabaseProvider.getDatabase(requireContext())

        val infoadminclient: TextView = view.findViewById(R.id.infoadminclient)

        val activity = requireActivity()
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.show()
            activity.supportActionBar?.apply {
                title = "Клиенты"
            }
        }
        val recyclerView: RecyclerView = view.findViewById(R.id.recClient)
        val itemDecoration = ItemOffsetDecoration(20)
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.layoutManager = LinearLayoutManager(context)
        GlobalScope.launch(Dispatchers.IO) {
            val selectClientList = db.userDao().selectCLient()
            withContext(Dispatchers.Main) {
                val adapter = AdapterUser(selectClientList)
                recyclerView.adapter = adapter
                if (adapter.itemCount == 0) {
                    infoadminclient.visibility = View.VISIBLE
                } else {
                    infoadminclient.visibility = View.GONE
                }
            }
        }
        return view
    }


}