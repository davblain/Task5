package com.example.gemini.task5.sql

import android.provider.BaseColumns

/**
 * Created by Gemini on 12.03.2018.
 */
object TaskContract {

    object TaskEntry: BaseColumns  {
        const val TABLE_NAME = "task"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_STATE = "state"
        const val COLUMN_NAME_TIMESTAMP = "timestamp"
    }
}