package com.example.gemini.task5.sql

import com.example.gemini.task5.model.Task

/**
 * Created by Gemini on 12.03.2018.
 */
interface TaskDao {
    fun fetchAllTasks():List<Task>
    fun fetchCompletedTasks():List<Task>
    fun addTask(task:Task):Task
    fun updateTask(task:Task)
    fun findById(id: Long):Task?;
    fun deleteById(id:Long)
}