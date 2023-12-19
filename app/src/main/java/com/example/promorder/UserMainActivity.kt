package com.example.promorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb

class UserMainActivity : AppCompatActivity() {
    private lateinit var db: RoomDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        loadFragment(UserWindowProduct())
        db = DatabaseProvider.getDatabase(applicationContext)
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerUser,fragment)
        transaction.commit()
    }
}