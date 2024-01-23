package com.example.lokflix

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.lokflix.Views.Tela_Login

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       Handler(Looper.getMainLooper()).postDelayed({
           val intent = Intent(this,Tela_Login::class.java)
           startActivity(intent)
           finish()
       },3000)














    }
}