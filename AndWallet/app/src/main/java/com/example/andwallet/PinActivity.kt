package com.example.andwallet

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pin.*

class PinActivity : AppCompatActivity() {
    companion object {
        const val PIN = "5738"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        btnStart.setOnClickListener {
            if (etNumberPassword.text.toString()==PIN) {
                var startMain = Intent(this, MainActivity::class.java)
                startActivity(startMain)
            } else {
                Toast.makeText(this, getString(R.string.wrong_password), Toast.LENGTH_LONG).show()
            }
        }
    }

}