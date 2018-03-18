package com.example.gemini.task5.model

import java.sql.Timestamp

/**
 * Created by Gemini on 12.03.2018.
 */
data class Task(var _id:Long?,var  description:String,var  state: Boolean? = false, var timestamp: String?) {
}