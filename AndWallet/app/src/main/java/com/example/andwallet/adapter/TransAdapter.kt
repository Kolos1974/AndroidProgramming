package com.example.andwallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.andwallet.MainActivity
import com.example.andwallet.R
import com.example.andwallet.data.AppDatabase
import com.example.andwallet.data.Trans
import com.example.andwallet.touch.TransTouchHelperCallback
import kotlinx.android.synthetic.main.trans_item.view.*
import java.util.*

class TransAdapter : RecyclerView.Adapter<TransAdapter.ViewHolder>, TransTouchHelperCallback {

    var transItems = mutableListOf<Trans>()

    val context: Context

    constructor(context: Context, transList: List<Trans>) : super() {
        this.context = context
        transItems.addAll(transList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val transView = LayoutInflater.from(context).inflate(
            R.layout.trans_item,
            parent, false
        )
        return ViewHolder(transView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var transItem = transItems[position]

        holder.tvDetail.text = transItem.detail
        holder.price.text = transItem.price.toString()
        holder.sign.text = transItem.sign.toString()

        if (transItem.sign.toString().toInt() == 1) {
            holder.ivItemLogo.setImageResource(R.drawable.income)
        } else {
            holder.ivItemLogo.setImageResource(R.drawable.expanse)
        }

        holder.btnDel.setOnClickListener {
            deleteTrans(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return transItems.size
    }

    fun addTransItem(transItem: Trans) {
        transItems.add(transItem)
        notifyItemInserted(transItems.lastIndex)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDetail: TextView = itemView.tvDetail  // etDetail vagy tvDetail??
        var price: TextView = itemView.tvPrice
        var sign: TextView = itemView.tvSign
        var btnDel = itemView.btnDel
        var ivItemLogo: ImageView = itemView.ivItemLogo
    }

    override fun onDismissed(position: Int) {
        deleteTrans(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(transItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun deleteTrans(position: Int) {
        var transDelete = transItems.get(position)
        if ((context as MainActivity).enoughBalance(transDelete.price * transDelete.sign)) {
            Thread {

                AppDatabase.getInstance(context).transDao().deleteTrans(transDelete)
                (context as MainActivity).runOnUiThread {
                    transItems.removeAt(position)
                    notifyItemRemoved(position)

                    // Egyenleg módosítása
                    (context as MainActivity).modifiedBalance(transDelete.price * transDelete.sign * (-1))
                }
            }.start()
        } else {
            (context as MainActivity).showNotEnoughMoney()
        }
    }

    fun deleteAll() {
        Thread {
            AppDatabase.getInstance(context).transDao().deleteAllTrans()
            (context as MainActivity).runOnUiThread {
                transItems.removeAll(transItems)
                notifyDataSetChanged()
            }
        }.start()
    }
}