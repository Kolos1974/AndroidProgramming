package com.example.andwallet

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.andwallet.adapter.TransAdapter
import com.example.andwallet.data.AppDatabase
import com.example.andwallet.data.Trans
import com.example.andwallet.touch.TransRecyclerTouchCallBack
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity(), TransDialog.TransHandler {

    lateinit var transAdapter: TransAdapter
    var balance: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        btnSumma.setOnClickListener{
            var startSumma = Intent(this, SummaryActivity::class.java)
            startActivity(startSumma)
        }

    }

    private fun initRecyclerView() {
        Thread {
            var transList = AppDatabase.getInstance(this@MainActivity).transDao().getAllTrans()

            runOnUiThread {

                transAdapter = TransAdapter(this, transList)
                recyclerTransaction.adapter = transAdapter

                val touchCallBack = TransRecyclerTouchCallBack(transAdapter)
                val itemTouchHelper = ItemTouchHelper(touchCallBack)
                itemTouchHelper.attachToRecyclerView(recyclerTransaction)

                // Egyenleg kiírása
                transList.forEach {
                    balance = balance + (it.price * it.sign)
                }
                showBalance()
            }
        }.start()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_andwallet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add_item) {
            TransDialog().show(supportFragmentManager, "Dialog")
        } else if (item.itemId == R.id.action_delete_all) {
            deleteAllData()

        } else if (item.itemId == R.id.action_exit) {
            finish()
        }
        return true
    }

    private fun deleteAllData() {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setMessage("Are you sure you want to delete all Data?")
            .setCancelable(false)
            .setPositiveButton(
                "Yes",
                DialogInterface.OnClickListener { dialog, id -> transAdapter.deleteAll() })
            .setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
        val alert = builder.create()
        alert.show()

        balance = 0
        showBalance()
    }

    private fun clearInputFields() {
        //etDetail.text.clear()
        //etPrice.text.clear()
        //rbtnIncome.isChecked = true
    }

    override fun transCreated(transItem: Trans) {
        if (enoughBalance(transItem.price * transItem.sign)) {
            Thread {
                AppDatabase.getInstance(this@MainActivity).transDao().insertTrans(transItem)

                runOnUiThread {
                    transAdapter.addTransItem(transItem)
                    balance = balance + (transItem.price * transItem.sign)
                    showBalance()
                }

            }.start()
        } else {
            showNotEnoughMoney()
        }
    }

    fun showBalance() {
        tvBalance.text = "Balance: " + balance.toString()
    }

    fun modifiedBalance(price: Int) {
        balance = balance + price
        showBalance()
    }

    fun enoughBalance(price: Int): Boolean {
        var result = true
        if ((price < 0) &&  ((balance + price) < 0)){
            result = false
        }
        return result
    }

    fun showNotEnoughMoney() {
        Toast.makeText(this, "You haven't enough money!", Toast.LENGTH_LONG).show()
    }

}