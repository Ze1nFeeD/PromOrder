package com.example.promorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserMainActivity : AppCompatActivity() {
    private lateinit var db: RoomDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        loadFragment(UserWindowProduct())
        db = DatabaseProvider.getDatabase(applicationContext)


        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavUser)
        bottomNav.setOnNavigationItemSelectedListener{
                item ->
            when(item.itemId)
            {
                R.id.product -> {
                    loadFragment(UserWindowProduct())
                    true
                }
                R.id.order -> {
                    loadFragment(UserOrderWindow())
                    true
                }
                else -> false
            }
        }

    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerUser,fragment)
        transaction.commit()
    }
}