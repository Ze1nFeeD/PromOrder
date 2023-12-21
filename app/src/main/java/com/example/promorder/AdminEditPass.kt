package com.example.promorder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import com.example.promorder.room.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdminEditPass : AppCompatActivity() {
    private lateinit var db: RoomDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_edit_pass)
        supportActionBar?.apply {
            title = "Cмена пароля"
        }
        val passfirst:EditText = findViewById(R.id.firstpass)
        val passsecond:EditText = findViewById(R.id.secondpass)
        val btnEditAdmin: Button = findViewById(R.id.btnEditAdmin)
        val btnCanEditAdmin: Button = findViewById(R.id.btnCanEditAdmin)
        db = DatabaseProvider.getDatabase(applicationContext)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "") ?: ""
        btnEditAdmin.setOnClickListener {
            var isValid = true

            if (passfirst.text.toString().isBlank() || passfirst.text.toString().contains(" ")) {
                passfirst.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Поле 'Логин' не может быть пустым", Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                passfirst.setBackgroundResource(R.drawable.bg)
            }


            if (passsecond.text.toString().isBlank() || passsecond.text.toString().contains(" ")) {
                passsecond.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Поле 'Пароль' не может быть пустым", Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                passsecond.setBackgroundResource(R.drawable.bg)
            }
            if (passfirst.text.toString() != passsecond.text.toString()) {
                passsecond.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Пароли должны совпадать", Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                passsecond.setBackgroundResource(R.drawable.bg)
            }

            if (isValid) {
                lifecycleScope.launch {
                    try {
                        withContext(Dispatchers.IO) {
                            db.userDao().resPass(
                                name = username.toString(),
                                newpass = passsecond.text.toString()
                            )
                        }
                        startActivity(Intent(this@AdminEditPass, AdminActivity::class.java))
                    } catch (e: Exception) {
                        Log.d("123","${e.message}")
                        Toast.makeText(
                            this@AdminEditPass,
                            "Ошибка: ${e.message}",

                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                // Вывести сообщение о некорректных данных
            }
        }
        btnCanEditAdmin.setOnClickListener {
            startActivity(Intent(this@AdminEditPass, AdminActivity::class.java))
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this, AdminActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}