package com.example.theshayriworld.allactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.theshayriworld.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        val intent = Intent(this@MainActivity, ShayromePageActivity::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 5000)

    }
}