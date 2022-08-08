package com.example.tictactoe.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictactoe.MainActivity
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnStart.setOnClickListener {
            var startMain = Intent(this, MainActivity::class.java)
            startActivity(startMain)
        }
    }
}