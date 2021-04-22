package com.example.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.login.data.AppDatabase
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun login(view: View) {
        val email = findViewById<EditText>(R.id.login_email_input)
        val password = findViewById<EditText>(R.id.login_password_input)

        val deferredUser = GlobalScope.async {
            AppDatabase.getInstance(applicationContext).userDao().getByEmail(email.text.toString())
        }

        runBlocking {
            val user = deferredUser.await()
            if (user != null && user.password == password.text.toString()) {
                val intent = Intent(applicationContext, DashboardActivity::class.java)
                intent.putExtra(getString(R.string.user_name_key), user.name)
                startActivity(intent)
                Toast.makeText(applicationContext, R.string.login_success, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, R.string.login_failed, Toast.LENGTH_LONG).show()
            }
        }
    }
}