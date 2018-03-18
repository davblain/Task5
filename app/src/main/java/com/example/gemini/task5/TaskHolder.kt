package com.example.gemini.task5

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.gemini.task5.model.Task
import com.example.gemini.task5.sql.TaskDao
import com.example.gemini.task5.sql.TaskDaoImpl
import kotlinx.android.synthetic.main.task_item_row.view.*

/**
 * Created by Gemini on 12.03.2018.
 */
class TaskHolder(private val view: View): RecyclerView.ViewHolder(view) {
    private val context: Context = view.context
    private val taskDao: TaskDao = TaskDaoImpl.instance
    fun bindTask(task: Task){
        view.description.text = task.description
         if (task.state!!) {
             (view as CardView).setCardBackgroundColor(Color.GREEN)
         } else
             (view as CardView).setCardBackgroundColor(Color.WHITE)
    }

}