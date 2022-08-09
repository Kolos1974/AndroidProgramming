package com.example.todorecyclerviewdemo

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.todorecyclerviewdemo.adapter.TodoAdapter
import com.example.todorecyclerviewdemo.data.Todo
import com.example.todorecyclerviewdemo.databinding.ActivityScrollingBinding
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    private lateinit var binding: ActivityScrollingBinding

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding = ActivityScrollingBinding.inflate(layoutInflater)
        // setContentView(binding.root)

        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))

        todoAdapter = TodoAdapter(this)
        recyclerTodo.adapter = todoAdapter

        fab.setOnClickListener{
            TodoDialog().show(supportFragmentManager, "Dialog")
        }

    }

    override fun todoCreated(todo: Todo) {
        todoAdapter.addTodo(todo)
    }


}