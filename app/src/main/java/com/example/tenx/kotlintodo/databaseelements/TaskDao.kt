package com.example.tenx.kotlintodo.databaseelements

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAll() : LiveData<List<Task>>
    @Insert
    fun insert(t : Task)
    @Query("DELETE FROM task_table WHERE uid = :id")
    fun delete(id : Int)
    @Query("DELETE FROM task_table")
    fun deleteAll()
}