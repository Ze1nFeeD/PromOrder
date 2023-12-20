package com.example.promorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.promorder.room.DatabaseProvider
import com.example.promorder.room.RoomDb
import com.example.promorder.room.UserEntity
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {
    private lateinit var db: RoomDb
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val username: EditText = findViewById(R.id.username)
        val password: EditText = findViewById(R.id.password)
        val nameorg: EditText = findViewById(R.id.nameorg)
        val inn: EditText = findViewById(R.id.inn)
        val ogrn: EditText = findViewById(R.id.ogrn)

        db = DatabaseProvider.getDatabase(applicationContext)
        val btnReg: Button = findViewById(R.id.btnReg)

        val usernameText: EditText = findViewById(R.id.username)
        val passwordText: EditText = findViewById(R.id.password)
        val nameorgText: EditText = findViewById(R.id.nameorg)
        val innText: EditText = findViewById(R.id.inn)
        val ogrnText: EditText = findViewById(R.id.ogrn)

        btnReg.setOnClickListener {
            var isValid = true


            if (usernameText.text.toString().isBlank() || usernameText.text.toString().contains(" ")) {
                usernameText.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Поле 'Логин' не может быть пустым",Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                usernameText.setBackgroundResource(R.drawable.bg)
            }


            if (passwordText.text.toString().isBlank() || passwordText.text.toString().contains(" ")) {
                passwordText.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Поле 'Пароль' не может быть пустым",Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                passwordText.setBackgroundResource(R.drawable.bg)
            }


            if (nameorgText.text.toString().isBlank() || nameorgText.text.toString().contains(" ")) {
                nameorgText.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Поле 'Название организации' не может быть пустым",Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                nameorgText.setBackgroundResource(R.drawable.bg)
            }


            if (!innText.text.toString().matches(Regex("^[0-9]+$"))) {
                innText.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Поле 'ИНН' не может быть пустым и должно содержать только цифры",Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                innText.setBackgroundResource(R.drawable.bg)
            }


            if (!ogrnText.text.toString().matches(Regex("^[0-9]+$"))) {
                ogrnText.setBackgroundResource(R.drawable.bg_red_edittext)
                Toast.makeText(applicationContext,"Поле 'ОГРН' не может быть пустым и должно содержать только цифры",Toast.LENGTH_SHORT).show()
                isValid = false
            } else {
                ogrnText.setBackgroundResource(R.drawable.bg)
            }

            if (isValid) {
                lifecycleScope.launch {
                    try {
                        db.userDao().insertUser(
                            UserEntity(
                                name = username.text.toString(),
                                password = password.text.toString(),
                                nameorg = nameorg.text.toString(),
                                inn = inn.text.toString(),
                                ogrn = ogrn.text.toString(),
                                role = 1
                            )
                        )
                        startActivity(Intent(this@RegistrationActivity, MainActivity::class.java))
                    } catch (e: Exception) {
                        Toast.makeText(
                            this@RegistrationActivity,
                            "Не уникальный логин",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                // Вывести сообщение о некорректных данных
            }

        }
        val btncan:Button = findViewById(R.id.btnCan)
        btncan.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity, MainActivity::class.java))
        }
    }
}