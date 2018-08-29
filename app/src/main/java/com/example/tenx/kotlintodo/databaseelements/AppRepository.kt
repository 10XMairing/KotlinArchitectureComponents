package com.example.tenx.kotlintodo.databaseelements

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class AppRepository constructor(application: Application){
    val mDb = AppDatabase.getInstance(application)
    val mDao = mDb.taskDao()
    companion object {
        const  val CMD_INSERT = 1
        const  val CMD_DELETE = 2
        const  val CMD_DELETEALL = 3
    }



    fun getAll() : LiveData<List<Task>>{
        return mDao.getAll()
    }

    fun insert(task: Task){
        DbWorkerAsync(mDao).execute(CMD_INSERT, task)
    }
    fun delete(id : Int){
        DbWorkerAsync(mDao).execute(CMD_DELETE, id)
    }
    fun deleteAll(){
        DbWorkerAsync(mDao).execute(CMD_DELETEALL)
    }





    class DbWorkerAsync(private val dao: TaskDao) : AsyncTask<Any, Void, String>(){


        override fun doInBackground(vararg params: Any?):String {
            if( params[0] as Int == CMD_INSERT){
                dao.insert(params[1] as Task)
            }
            if(params[0] == CMD_DELETEALL){
                dao.deleteAll()
            }
            if(params[0] == CMD_DELETE){
                dao.delete(params[1] as Int)
            }

            return ""
        }
    }
}