package com.example.tenx.kotlintodo.databaseelements

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class AppViewModel constructor(application: Application): AndroidViewModel(application) {

    val mRepo : AppRepository = AppRepository(application)

    fun getAll() : LiveData<List<Task>> {
        return mRepo.getAll()
    }

    fun insert(quotes: Task){
        mRepo.insert(quotes)
    }
    fun delete(id : Int){
        mRepo.delete(id)
    }
    fun deleteAll(){
        mRepo.deleteAll()
    }
}