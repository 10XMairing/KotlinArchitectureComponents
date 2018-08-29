package com.example.tenx.kotlintodo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.example.tenx.kotlintodo.adapters.TaskAdapter
import com.example.tenx.kotlintodo.databaseelements.AppViewModel
import com.example.tenx.kotlintodo.databaseelements.Task
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_input_task.*
import kotlinx.android.synthetic.main.dialog_input_task.view.*

class MainActivity : AppCompatActivity() {
    var adapter : TaskAdapter? = null
    var vm : AppViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProviders.of(this).get(AppViewModel::class.java)


        //setting up the adapter
        adapter = TaskAdapter(this)
        recycler_task.layoutManager = LinearLayoutManager(this)
        recycler_task.adapter = adapter


        //setting observer on task data
        vm!!.getAll().observe(this, Observer {
            adapter!!.insertAndRefresh(it!!)
        })


        //setting up fab onclick for input
        fab_add_task.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val view : View =LayoutInflater.from(this).inflate(R.layout.dialog_input_task, null, false)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            view.btn_save.setOnClickListener{_->
                var task = view.tv_task_input.text.toString()
                var time = view.tv_task_input.tv_time_input
                vm!!.insert(Task(task, System.currentTimeMillis()))
                dialog.dismiss()

            }
        }

    }
}
