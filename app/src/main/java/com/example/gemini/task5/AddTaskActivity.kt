package com.example.gemini.task5

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.gemini.task5.model.Task
import com.example.gemini.task5.sql.TaskDaoImpl
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {
    val taskDao = TaskDaoImpl.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        completeButton.setOnClickListener{ addTask()}
    }

    private fun addTask() {
        taskDao.addTask(Task(null,descriptionTextEdit.text.toString(),false,null))
        setResult(Activity.RESULT_OK)
        finish()
    }
    companion object {
        fun newIntent(context: Context):Intent {
            return Intent(context,AddTaskActivity::class.java)
        }
    }
}
