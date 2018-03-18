package com.example.gemini.task5.sql

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.example.gemini.task5.model.Task
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Gemini on 12.03.2018.
 */
class TaskDaoImpl private constructor(val dbHelper: TaskDbHelper = TaskDbHelper.instance):TaskDao {


    override fun fetchAllTasks(): List<Task> {
        val list = ArrayList<Task>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(TaskContract.TaskEntry.TABLE_NAME,null,null,null,null,null,null)
        with(cursor) {
            while (moveToNext()) {
                list.add(readOneRow(cursor))
            }
        }
        cursor.close()
        return list
    }

    override fun fetchCompletedTasks(): List<Task> {
       return  fetchAllTasks().filter { task -> task.state!! }
    }

    override fun addTask(task: Task): Task {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION,task.description)
            put(TaskContract.TaskEntry.COLUMN_NAME_STATE,task.state)
            put(TaskContract.TaskEntry.COLUMN_NAME_TIMESTAMP,getDateTime())
        }
        val newRowId = db.insert(TaskContract.TaskEntry.TABLE_NAME,null,values)
        return findById(newRowId)!!
    }

    override fun findById(id: Long): Task? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(TaskContract.TaskEntry.TABLE_NAME,null,"${BaseColumns._ID} = ?",arrayOf(id.toString()),null,null,null)
        cursor.moveToFirst()
        val task = readOneRow(cursor)
        cursor.close()
        return task
    }

    override fun updateTask(task: Task) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION,task.description)
            put(TaskContract.TaskEntry.COLUMN_NAME_STATE,task.state)
            put(TaskContract.TaskEntry.COLUMN_NAME_TIMESTAMP,getDateTime())
        }
        db.update(TaskContract.TaskEntry.TABLE_NAME,values,"${BaseColumns._ID} = ?", arrayOf(task._id.toString()))
    }

    override fun deleteById(id: Long) {
        dbHelper.writableDatabase .delete(TaskContract.TaskEntry.TABLE_NAME,"${BaseColumns._ID} = ?",arrayOf(id.toString()))
    }

    private fun readOneRow(cursor:Cursor):Task {
        with(cursor) {
            val description = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION))
            val id = getInt(getColumnIndexOrThrow(BaseColumns._ID))
            val state = when (getInt(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_STATE))) {
                1 -> true
                0 -> false
                else -> false
            }
            val timestamp = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_TIMESTAMP))
            return Task(id.toLong(),description,state,timestamp)
        }
    }

    private fun getDateTime(): String {
        val dateFormat = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
    private object Holder { val INSTANCE = TaskDaoImpl() }
    companion object {
        val instance: TaskDaoImpl by lazy { Holder.INSTANCE}
    }
}