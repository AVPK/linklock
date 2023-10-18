package com.example.smartwatchunlocker.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.smartwatchunlocker.R
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val localTime = LocalTime.now()
//        val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
        val dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm")
        findViewById<TextView>(R.id.time_tv).text = localTime.format(dateTimeFormatter)

        findViewById<CardView>(R.id.login_btn).setOnClickListener {
            startActivity(Intent(this@SplashScreen, MainScreen::class.java))
            finish()
        }
    }
}