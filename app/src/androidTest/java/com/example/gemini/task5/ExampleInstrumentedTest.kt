package com.example.gemini.task5

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.gemini.task5.R.id.description
import com.example.gemini.task5.model.Task
import com.example.gemini.task5.sql.TaskDaoImpl
import com.example.gemini.task5.sql.TaskDbHelper

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.gemini.task5", appContext.packageName)
    }
    @Test
    fun testDao() {
        val taskDao= TaskDaoImpl(TaskDbHelper(InstrumentationRegistry.getTargetContext()))
        taskDao.addTask(Task(null,"Test",false,null))
        assertEquals(taskDao.findById(1)?.description,"Test")
    }
}
