package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnReset.setOnClickListener {
            ticView.resetGame()
            revealTicTacToe()
        }

    }

    public fun showText(text: String){
        tvData.text = text
    }

    fun revealTicTacToe(){
        val x = ticView.getRight()
        val y = ticView.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(ticView.getWidth().toDouble(),
        ticView.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            ticView,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        anim.duration = 1000

        ticView.setVisibility(View.VISIBLE)
        anim.start()

    }



}