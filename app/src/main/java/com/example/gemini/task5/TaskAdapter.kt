package com.example.gemini.task5

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.gemini.task5.model.Task
import com.example.gemini.task5.sql.TaskDao
import com.example.gemini.task5.sql.TaskDaoImpl

/**
 * Created by Gemini on 12.03.2018.
 */
class TaskAdapter:RecyclerView.Adapter<TaskHolder>() {


    private val tasks:ArrayList<Task> = ArrayList();
    private val taskDao : TaskDao = TaskDaoImpl.instance

    init {
        fetchTasks()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val inflatedView = parent.inflate(R.layout.task_item_row)
        return TaskHolder(inflatedView)
    }

    override fun getItemCount(): Int =  tasks.size

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
       holder.bindTask(tasks[position])
    }

     fun fetchTasks() {
        tasks.clear()
        tasks.addAll(taskDao.fetchAllTasks())
        notifyDataSetChanged()
    }

    fun deleteTask(position: Int) {
        taskDao.deleteById(tasks[position]._id!!)
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }
    fun fetchUpdatedTask(position:Int) {
        tasks[position]= taskDao.findById(tasks[position]._id!!)!!
        notifyItemChanged(position)
    }
    fun markCompleted(position: Int) {
        tasks[position].state = true
        taskDao.updateTask(tasks[position])
        notifyItemChanged(position)
    }
    fun getTask(position: Int):Task {
        return tasks[position]
    }
    fun findPositionByTaskId( taskId: Long ):Int {
        return tasks.indexOfFirst { it._id!! == taskId }
    }
    fun  addTask(description: String) {
        val task = taskDao.addTask(Task(null,description,false,null))
        tasks.add(task)
        notifyItemInserted(tasks.size-1)
    }

}