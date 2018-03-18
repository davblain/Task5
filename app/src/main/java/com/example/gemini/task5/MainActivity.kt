package com.example.gemini.task5

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.gemini.task5.EditTaskActivity.Companion.TASK_ID
import com.example.gemini.task5.model.Task
import com.example.gemini.task5.sql.TaskDao
import com.example.gemini.task5.sql.TaskDaoImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_EDIT_TASK = 1
    private val REQUEST_ADD_TASK = 2
    private lateinit var adapter: TaskAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linearLayoutManager = LinearLayoutManager(this)
        tasksView.layoutManager = linearLayoutManager
        adapter = TaskAdapter()
        tasksView.adapter = adapter
        tasksView.addOnItemTouchListener(MyTouchListener (this@MainActivity,tasksView,object: MyTouchListener.OnTouchActionListener{
            override fun onLeftSwipe(view: View, position: Int) {
                adapter.deleteTask(position)
            }

            override fun onRightSwipe(view: View, position: Int) {
                adapter.markCompleted(position)
            }

            override fun onLongPress(view: View, position: Int) {
                startActivityForResult(EditTaskActivity.newIntent(this@MainActivity,adapter.getTask(position)),REQUEST_EDIT_TASK)
            }
        }))
        fab.setOnClickListener{ startActivityForResult(AddTaskActivity.newIntent(this),REQUEST_ADD_TASK)}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( requestCode == REQUEST_ADD_TASK && resultCode == RESULT_OK) {

            adapter.fetchTasks()
        }
        if(requestCode == REQUEST_EDIT_TASK && resultCode == RESULT_OK) {
           notifyTaskUpdated(data?.extras?.getLong(TASK_ID)!!)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun notifyTaskUpdated(taskId:Long) {
        adapter.fetchUpdatedTask(adapter.findPositionByTaskId(taskId))
    }




}
