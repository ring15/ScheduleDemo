package com.founq.sdk.scheduledemo

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by ring on 2019/8/21.
 */
interface SchedyleImpl {
    fun initSchedule(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
    fun setRowParameters(mWidth: Int, mHeight: Int, mRowHeight: Int, mColumnWidth: Int, mSpace: Int)
    fun setCurrentMonth(mCurrentMonth : Int)
    fun setDateList(mDateList: List<String>)
    fun drawRow(canvas: Canvas?)
    fun setColumnParameters(mRowHeight: Int, mColumnHeight: Int, mColumnWidth: Int, mSpace: Int)
    fun drawColumn(canvas: Canvas?)
    fun setCenterRectParameters(mWidth: Int,mRowHeight: Int, mColumnHeight: Int, mColumnWidth: Int, mSpace: Int)
    fun drawCenterRect(canvas: Canvas?)
    fun touchDown(event: MotionEvent)
    fun touchUp(event: MotionEvent)
    fun touchMove(event: MotionEvent)
}