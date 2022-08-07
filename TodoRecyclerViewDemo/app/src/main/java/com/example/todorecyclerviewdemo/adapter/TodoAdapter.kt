package com.example.todorecyclerviewdemo.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todorecyclerviewdemo.data.Todo
import kotlinx.android.synthetic.main.todo_row.view.*


//class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>{
class TodoAdapter {

    var todoItems = mutableListOf<Todo>(
        Todo("2022. 07. 16.", false, "Got to out")
    )

    val context: Context
    constructor(context: Context, todoList: List<Todo>) : super() {
        this.context = context
        //todoItems.addAll(todoList)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate = itemView.tvDate
        var cbDone = itemView.cbDone
    }
}
