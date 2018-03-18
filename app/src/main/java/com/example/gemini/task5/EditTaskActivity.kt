package com.example.gemini.task5

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.gemini.task5.model.Task
import com.example.gemini.task5.sql.TaskDaoImpl
import kotlinx.android.synthetic.main.activity_edit_task.*

class EditTaskActivity : AppCompatActivity() {

    val taskDao = TaskDaoImpl.instance
    private var idOfTask:Long = 0
    private lateinit var task:Task
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)
        if (savedInstanceState==null) {
            idOfTask = intent.extras[TASK_ID] as Long
            bindTask(taskDao.findById(idOfTask)!!)
        }

    }

    private fun bindTask(task: Task) {
        this.task = task
        editedDescription.setText(task.description)
        confirmEditButton.setOnClickListener { updateTask()  }
    }

    fun updateTask() {
        val intent = Intent()
        intent.putExtra(TASK_ID,idOfTask)
        task.description = editedDescription.text.toString()
        taskDao.updateTask(task)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
    companion object {
        val TASK_ID = "task_id"
        fun newIntent(context: Context, task: Task): Intent {
            val intent = Intent(context,EditTaskActivity::class.java)
            intent.putExtra(TASK_ID,task._id!!)
            return intent;
        }

    }

}
