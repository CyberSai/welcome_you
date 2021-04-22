package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val userName = intent.getStringExtra(getString(R.string.user_name_key))
        val name = findViewById<TextView>(R.id.dashboard_name_label)
        name.text = userName
    }

    fun logout(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        Toast.makeText(applicationContext, R.string.logout_success, Toast.LENGTH_LONG).show()
    }
}