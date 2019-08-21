package com.founq.sdk.scheduledemo.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.founq.sdk.scheduledemo.ScheduleSupport

/**
 * Created by ring on 2019/8/21.
 */
class ScheduleView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var mWidth: Int = 0
    var mHeight: Int = 0

    var scheduleSupport = ScheduleSupport()

    init {
        scheduleSupport.initSchedule(context, attrs, defStyleAttr)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        mHeight = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        scheduleSupport.setColumnParameters(mWidth / 8, mWidth / 8,mWidth / 8, 60)
        scheduleSupport.setCenterRectParameters(mWidth,mWidth / 8, mWidth / 8,mWidth / 8, 42)
        scheduleSupport.setRowParameters(mWidth, mHeight, mWidth / 8, mWidth / 8, 42)
        scheduleSupport.mHeight = mHeight
        scheduleSupport.mRowHeight = mWidth / 8
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        scheduleSupport.drawColumn(canvas)
        scheduleSupport.drawCenterRect(canvas)
        scheduleSupport.drawRow(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                scheduleSupport.touchDown(event)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                scheduleSupport.touchUp(event)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                scheduleSupport.touchMove(event)
                invalidate()
            }
        }
        return true
    }
}