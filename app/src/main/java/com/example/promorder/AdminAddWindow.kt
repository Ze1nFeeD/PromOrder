package com.example.promorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import com.example.promorder.room.UserEntity
import kotlinx.coroutines.launch

class AdminAddWindow : AppCompatActivity() {
    private lateinit var db: RoomDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_add_window)
        val btnRegAdmin: Button = findViewById(R.id.btnRegAdmin)
        val adminname: EditText = findViewById(R.id.adminname)
        val passadmin: EditText = findViewById(R.id.passadmin)
        val btnCanAdmin: Button = findViewById(R.id.btnCanAdmin)
        db = DatabaseProvider.getDatabase(applicationContext)

        supportActionBar?.apply {
            title = "Добавление администратора"
        }

        btnRegAdmin.setOnClickListener {
            var isValid = true

            if (adminname.text.toString().isBlank() || adminname.text.toString().contains(" ")) {
                adminname.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Поле 'Логин' не может быть пустым", Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                adminname.setBackgroundResource(R.drawable.bg)
            }


            if (passadmin.text.toString().isBlank() || passadmin.text.toString().contains(" ")) {
                passadmin.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Поле 'Пароль' не может быть пустым", Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                passadmin.setBackgroundResource(R.drawable.bg)
            }
            if (isValid) {
                lifecycleScope.launch {
                    try {
                        db.userDao().insertUser(
                            UserEntity(
                                name = adminname.text.toString(),
                                password = passadmin.text.toString(),
                                nameorg = "admin",
                                inn = "admin",
                                ogrn = "admin",
                                role = 0
                            )
                        )
                        startActivity(Intent(this@AdminAddWindow, AdminActivity::class.java))
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@AdminAddWindow,
                            "Не уникальный логин",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                // Вывести сообщение о некорректных данных
            }
        }
        btnCanAdmin.setOnClickListener {
            startActivity(Intent(this@AdminAddWindow, AdminActivity::class.java))
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