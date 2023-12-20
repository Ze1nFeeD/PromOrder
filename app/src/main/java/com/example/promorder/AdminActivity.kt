package com.example.promorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminActivity : AppCompatActivity() {
    private lateinit var db: RoomDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        loadFragment(AdminWindowProduct())
        db = DatabaseProvider.getDatabase(applicationContext)


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
                    loadFragment(AdminOrderWindow())
                    true
                }
                R.id.client -> {
                    loadFragment(AdminWindowClient())
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