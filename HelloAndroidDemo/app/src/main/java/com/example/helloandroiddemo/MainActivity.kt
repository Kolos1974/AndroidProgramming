package com.example.helloandroiddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloandroiddemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /* Régi módszer
        setContentView(R.layout.activity_main)

        val tvData = findViewById<TextView>(R.id.tvData)
        tvData.text = Date(System.currentTimeMillis()).toString()
        */

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTime.setOnClickListener{
            binding.tvBinding.text = Date(System.currentTimeMillis()).toString()
        }



    }
}