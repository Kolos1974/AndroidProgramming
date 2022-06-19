package com.example.myfirstandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myfirstandroidapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var rand = Random()

        binding.btnTime.setOnClickListener {
            if (binding.etData.text.isNotEmpty()) {


                var num = rand.nextInt(100)

                var currentTime =
                    "$num ${binding.etData.text.toString()} ${Date(System.currentTimeMillis()).toString()}"

                binding.tvData.text = currentTime

                Toast.makeText(
                    this, currentTime,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                binding.etData.error = "Ez nem lehet üres!"
            }
        }
    }
}