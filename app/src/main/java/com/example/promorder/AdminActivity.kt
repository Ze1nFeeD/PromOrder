package com.example.promorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        supportActionBar?.apply {
            title = "Главное меню"
        }

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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this, MainActivity::class.java))
                return true
            }
            R.id.respass -> {
                startActivity(Intent(this, AdminEditPass::class.java))
                return true
            }
            R.id.addnewadmin -> {
                startActivity(Intent(this, AdminAddWindow::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.admin_menu, menu)
        return true
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}