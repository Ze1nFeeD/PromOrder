package com.example.promorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import com.example.promorder.room.UserEntity

class AdminActivity : AppCompatActivity() {
    private lateinit var db: RoomDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        db = DatabaseProvider.getDatabase(applicationContext)
        val recyclerView: RecyclerView = findViewById(R.id.recProd)
        Thread{
            val selectProdList=db.userDao().selectProd()
            val adapter = AdapterProd(selectProdList)
            recyclerView.adapter = adapter
        }.start()
    }
}