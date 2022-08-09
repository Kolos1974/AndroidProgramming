package com.example.todorecyclerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todorecyclerviewdemo.R
import com.example.todorecyclerviewdemo.data.Todo
import kotlinx.android.synthetic.main.todo_row.view.*


class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>{

    var todoItems = mutableListOf<Todo>(
        Todo("2022. 07. 16.", false, "Got to out"),
        Todo("2022. 07. 22.", false, "Learning"),
        Todo("2022. 08. 06.", false, "Washing")
    )

    val context: Context
//  constructor(context: Context, todoList: List<Todo>) : super() {
    constructor(context: Context) : super() {
        this.context = context
        //todoItems.addAll(todoList)
    }


    // Egy adjuk meg, hogy nézzen ki egy sor
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoView = LayoutInflater.from(context).inflate(R.layout.todo_row,
            parent, false)
        return ViewHolder(todoView)
    }

    // Az egyes listaelemeknél mit rajzoljon ki
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var todoItem = todoItems[position]

        holder.tvDate.text = todoItem.createDate
        holder.cbDone.isChecked = todoItem.done
        holder.cbDone.text = todoItem.todoText
    }

    fun addTodo(todo: Todo){
        todoItems.add(todo)
        notifyItemInserted(todoItems.lastIndex)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate = itemView.tvDate
        var cbDone = itemView.cbDone
    }

}
