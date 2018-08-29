package com.example.tenx.kotlintodo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tenx.kotlintodo.R
import com.example.tenx.kotlintodo.databaseelements.Task
import kotlinx.android.synthetic.main.vh_task_listitem.view.*
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(private val mCtx : Context) : RecyclerView.Adapter<TaskAdapter.CustomVH>() {
    private var mList : ArrayList<Task>? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomVH {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.vh_task_listitem, p0, false)

        return CustomVH(view)
    }

    override fun getItemCount(): Int {
        return if(mList != null){
            mList!!.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(p0: CustomVH, p1: Int) {
        val sdf = SimpleDateFormat("h:mm:aa", Locale.ENGLISH)
        val time = Date(mList!!.get(p1).time!!)

        p0.tvTime.text = sdf.format(time)
        p0.tvTask.text = mList!!.get(p1).task
    }

    class CustomVH(view : View) : RecyclerView.ViewHolder(view){
        val tvTask = view.tv_task
        val tvTime = view.tv_task_time
    }

    fun insertAndRefresh(list : List<Task>){
        mList = list as ArrayList<Task>
        notifyDataSetChanged()
    }

}