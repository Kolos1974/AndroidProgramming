package com.example.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val demoAnim = AnimationUtils.loadAnimation(this, R.anim.demo_anim)

        demoAnim.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                // TODO("Not yet implemented")
            }

            override fun onAnimationEnd(animation: Animation?) {
                // TODO("Not yet implemented")
                Toast.makeText(this@MainActivity, "Anmimation ended", Toast.LENGTH_LONG).show()
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // TODO("Not yet implemented")
            }


        })



        btnAnim.setOnClickListener {
            // layoutMain.startAnimation(demoAnim)
            btnAnim.startAnimation(demoAnim)
        }
    }
}