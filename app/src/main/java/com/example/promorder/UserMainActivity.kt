package com.example.promorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserMainActivity : AppCompatActivity() {
    private lateinit var db: RoomDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        loadFragment(UserWindowProduct())
        db = DatabaseProvider.getDatabase(applicationContext)

        supportActionBar?.apply {
            title = "Главное меню"
        }

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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            startActivity(Intent(this, MainActivity::class.java))
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerUser,fragment)
        transaction.commit()
    }
}