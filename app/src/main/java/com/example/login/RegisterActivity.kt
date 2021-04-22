package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.login.data.AppDatabase
import com.example.login.data.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun goToLogin(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun register(view: View) {
        val name = findViewById<EditText>(R.id.register_name_input)
        val email = findViewById<EditText>(R.id.register_email_input)
        val password = findViewById<EditText>(R.id.register_password_input)

        val user = User(0,name.text.toString(),email.text.toString(),password.text.toString())

        GlobalScope.launch {
            AppDatabase.getInstance(applicationContext).userDao().create(user)
        }

        Toast.makeText(applicationContext, R.string.register_success, Toast.LENGTH_LONG).show()
    }
}