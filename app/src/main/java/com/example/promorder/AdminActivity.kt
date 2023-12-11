package com.example.promorder

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import com.example.promorder.room.UserEntity
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        val bottomNav:BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener{
            item ->
            when(item.itemId)
            {
                R.id.product -> {
                    loadFragment(AdminWindowProduct())
                    true
                }
                R.id.order -> {
                    Toast.makeText(this,"Заказы",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.client -> {
                    Toast.makeText(this,"Клиенты",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}