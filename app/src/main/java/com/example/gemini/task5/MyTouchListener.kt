package com.example.gemini.task5

import android.content.Context
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.R.attr.onClick



/**
 * Created by Gemini on 16.03.2018.
 */

open class MyTouchListener : RecyclerView.OnItemTouchListener {

    private  val mOnTouchActionListener: OnTouchActionListener
    private  val mGestureDetector: GestureDetectorCompat

    constructor(context: Context,recyclerView: RecyclerView,onTouchActionListener: OnTouchActionListener) {
        mOnTouchActionListener = onTouchActionListener;
        mGestureDetector = GestureDetectorCompat(context, object :GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
                try {
                    if (Math.abs(e1.y-e2.y)> SWIPE_MAX_OFF_PATH){
                        return false
                    }
                    val child:View? = recyclerView.findChildViewUnder(e1.x,e2.y)
                    val childPosition = recyclerView.getChildAdapterPosition(child)
                    if (e1.x - e2.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        if (child!=null) {
                            mOnTouchActionListener.onLeftSwipe(child,childPosition)
                        }
                    } else if (e2.x - e1.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        if (child!=null) {
                            mOnTouchActionListener.onRightSwipe(child,childPosition)
                        }
                    }

                } catch (e:Exception) {

                }
                return  false
            }

            override fun onLongPress(e: MotionEvent) {
                super.onLongPress(e)
                val child = recyclerView.findChildViewUnder(e.x, e.y)
                if (child!= null) {
                    val childPosition = recyclerView.getChildAdapterPosition(child)
                    mOnTouchActionListener.onLongPress(child, childPosition)
                }

            }
        })

    }

    interface OnTouchActionListener {
        fun onLeftSwipe(view: View, position: Int)
        fun onRightSwipe(view: View, position: Int)
        fun onLongPress(view: View,position: Int)
    }


    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        mGestureDetector.onTouchEvent(e)
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }
    companion object {
        private val SWIPE_MIN_DISTANCE = 120
        private val SWIPE_THRESHOLD_VELOCITY = 200
        private val SWIPE_MAX_OFF_PATH = 250
    }
}