package com.example.promorder

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

        btnReg.setOnClickListener {
            lifecycleScope.launch {
                try
                {
                db.userDao().insertUser(
                    UserEntity(
                        name = username.text.toString(),
                        password = password.text.toString(),
                        nameorg = nameorg.text.toString(),
                        inn = inn.text.toString(),
                        ogrn = ogrn.text.toString(),
                        role = 0
                    )
           )    }
                catch (e:Exception)
                {
                  Toast.makeText(this@RegistrationActivity,"Не уникальный логин",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}