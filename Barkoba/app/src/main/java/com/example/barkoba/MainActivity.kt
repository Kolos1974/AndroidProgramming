package com.example.barkoba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.barkoba.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var rand = Random()
    var guessNumber = 0
    var num = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnGuess.setOnClickListener {
            if (binding.etTextNumber.text.isNotEmpty()) {

                guessNumber++

                set_etResult()

                if (num < binding.etTextNumber.text.toString().toInt()){
                    Toast.makeText(this, "Your guess is higher",
                        Toast.LENGTH_LONG
                    ).show()
                }

                if (num > binding.etTextNumber.text.toString().toInt()){
                    Toast.makeText(this, "Your guess is lower",
                        Toast.LENGTH_LONG
                    ).show()
                }

                if (num == binding.etTextNumber.text.toString().toInt()){
                    Toast.makeText(this, "Congratulated! You find my number!!",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } else {
                binding.etTextNumber.error = "Meg kell adni egy számot!"
            }
        }

        // A másik gombnak egy függvényt adok meg
        binding.btnNewGame.setOnClickListener(::init)

        // Meghívom a new game gomb eseményét
        binding.btnNewGame.callOnClick()

    }

    private fun init(view: View) {
        num = rand.nextInt(100)
        guessNumber = 0
        set_etResult()
        binding.etTextNumber.text.clear()
    }

    private fun set_etResult(){
        binding.etResult.text = "Your guess number: $guessNumber"
    }


}