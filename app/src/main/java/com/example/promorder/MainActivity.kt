package com.example.promorder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import com.example.promorder.room.UserEntity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var db: RoomDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DatabaseProvider.getDatabase(applicationContext)
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val btnEnter: Button = findViewById(R.id.button)
        supportActionBar?.hide()
Thread {

    if (db.userDao().selectFind().isEmpty()) {
        lifecycleScope.launch {
            db.userDao().insertUser(
                UserEntity(
                    name = "0",
                    password = "0",
                    nameorg = "0",
                    inn = "0",
                    ogrn = "0",
                    role = 0
                )
            )
        }
    }
}.start()
        btnEnter.setOnClickListener {
            Thread {

                val userCur = db.userDao().select(
                    username = username.text.toString(),
                    password = password.text.toString()
                )
                if (userCur.isEmpty())
                {
                    runOnUiThread {
                        message(this@MainActivity, text = "Не правильный логин или пароль!")
                    }
                }
                else
                {
                    if(userCur.first().role == 0) {
                        runOnUiThread {
                            val sharedPreferencesAd = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                            val editor = sharedPreferencesAd.edit()
                            editor.putString("username", username.text.toString())
                            editor.apply()
                          startActivity(Intent(this, AdminActivity::class.java))
                        }
                    }
                    else
                    {
                        runOnUiThread {
                            val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                            val editor = sharedPreferences.edit()
                            editor.putString("username", username.text.toString())
                            editor.apply()
                            startActivity(Intent(this, UserMainActivity::class.java))
                        }
                    }
                }
            }.start()
        }
        val btnReg: TextView = findViewById(R.id.reg)
        btnReg.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }
}fun message(context: Context, text: String)
{
    Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
}