package com.example.gemini.task5.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.gemini.task5.App
import com.example.gemini.task5.sql.TaskContract

/**
 * Created by Gemini on 12.03.2018.
 */

class TaskDbHelper(context: Context = App.instance): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        private const val DATABASE_NAME = "Task5.db"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_ENTRIES =
                "CREATE TABLE ${TaskContract.TaskEntry.TABLE_NAME} (" +
                        "${BaseColumns._ID} INTEGER PRIMARY KEY," +"${TaskContract.TaskEntry.COLUMN_NAME_STATE} INTEGER,"+
                        "${TaskContract.TaskEntry.COLUMN_NAME_DESCRIPTION} TEXT," +
                        "${TaskContract.TaskEntry.COLUMN_NAME_TIMESTAMP} TEXT)"

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TaskContract.TaskEntry.TABLE_NAME}"

        val instance: TaskDbHelper by lazy { TaskDbHelper() }
    }
}