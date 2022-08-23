package com.example.andwallet

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.andwallet.adapter.TransAdapter
import com.example.andwallet.data.AppDatabase
import com.example.andwallet.touch.TransRecyclerTouchCallBack
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        var income = 0
        var expanse = 0

        Thread {
            var transListIncome = AppDatabase.getInstance(this).transDao().getSomeTrans(1)
            var transListExpanse = AppDatabase.getInstance(this).transDao().getSomeTrans(-1)

            runOnUiThread {
                transListIncome.forEach {
                    income = income + (it.price)
                }

                transListExpanse.forEach {
                    expanse = expanse + (it.price)
                }

                tvIncomeValue.text = income.toString()
                tvExpenseValue.text = expanse.toString()
                tvBalanceValue.text = (income-expanse).toString()
            }

        }.start()
    }
}