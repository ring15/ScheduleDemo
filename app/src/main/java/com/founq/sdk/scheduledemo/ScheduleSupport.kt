package com.founq.sdk.scheduledemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import com.founq.sdk.scheduledemo.widget.DrawCenterRect
import com.founq.sdk.scheduledemo.widget.DrawColumn
import com.founq.sdk.scheduledemo.widget.DrawRow

/**
 * Created by ring on 2019/8/21.
 */
class ScheduleSupport : SchedyleImpl {

    var drawRow = DrawRow()
    var drawColumn = DrawColumn()
    var drawCenterRect = DrawCenterRect()
    var lastY = 0
    var mStartPosition = 0
    var mHeight = 0
    var mRowHeight: Int = 0

    override fun setCurrentMonth(mCurrentMonth: Int) {
        drawRow.mCurrentMonth = mCurrentMonth
    }

    override fun setDateList(mDateList: List<String>) {
        drawRow.mDateList = mDateList
    }

    override fun setCenterRectParameters(mWidth: Int, mRowHeight: Int, mColumnHeight: Int, mColumnWidth: Int, mSpace: Int) {
        drawCenterRect.mWidth = mWidth
        drawCenterRect.mRowHeight = mRowHeight
        drawCenterRect.mColumnHeight = mColumnHeight
        drawCenterRect.mColumnWidth = mColumnWidth
        drawCenterRect.mSpace = mSpace
    }

    override fun drawCenterRect(canvas: Canvas?) {
        drawCenterRect.draw(canvas, 2, 2, 4, Color.parseColor("#FFEC94B5"), "编译原理")
        drawCenterRect.draw(canvas, 0, 0, 2, Color.parseColor("#FFB799EB"), "计算机网络")
        drawCenterRect.draw(canvas, 1, 6, 8, Color.parseColor("#FFE7F376"), "软件工程")
        drawCenterRect.draw(canvas, 4, 2, 3, Color.parseColor("#FFF04B40"),"Linux操作系统")
        drawCenterRect.draw(canvas, 6, 4, 6, Color.parseColor("#FF6CDCEB"), "人工智能")
    }
    override fun touchDown(event: MotionEvent) {
        if (drawColumn.getHeight() < mHeight - mRowHeight) {
            return
        }
        lastY = event.getY().toInt()
    }

    override fun touchUp(event: MotionEvent) {
        if (drawColumn.getHeight() < mHeight - mRowHeight) {
            return
        }
        var offsetY = event.getY().toInt() - lastY
        mStartPosition += offsetY
        if (mStartPosition > 0) {
            mStartPosition = 0
        } else if (mStartPosition + drawColumn.getHeight() < mHeight - mRowHeight) {
            mStartPosition = mHeight - drawColumn.getHeight() - mRowHeight
        }
    }

    override fun touchMove(event: MotionEvent) {
        if (drawColumn.getHeight() < mHeight - mRowHeight) {
            return
        }
        var y = event.getY().toInt()
        var offsetY = mStartPosition + y - lastY
        if (offsetY > 0) {
            setStartPosion(0)
        } else if (offsetY + drawColumn.getHeight() < mHeight - mRowHeight) {
            setStartPosion(mHeight - drawColumn.getHeight() - mRowHeight)
        } else {
            setStartPosion(offsetY)
        }
    }

    fun setStartPosion(mStartPosition: Int) {
        drawColumn.mStartPosition = mStartPosition
        drawCenterRect.mStartPosition = mStartPosition
    }

    override fun setColumnParameters(mRowHeight: Int, mColumnHeight: Int, mColumnWidth: Int, mSpace: Int) {
        drawColumn.mRowHeight = mRowHeight
        drawColumn.mColumnHeight = mColumnHeight
        drawColumn.mColumnWidth = mColumnWidth
        drawColumn.mSpace = mSpace
    }

    override fun drawColumn(canvas: Canvas?) {
        drawColumn.draw(canvas)
    }

    override fun drawRow(canvas: Canvas?) {
        drawRow.draw(canvas)
    }

    override fun setRowParameters(mWidth: Int, mHeight: Int, mRowHeight: Int, mColumnWidth: Int, mSpace: Int) {
        drawRow.mWidth = mWidth
        drawRow.mHeight = mHeight
        drawRow.mRowHeight = mRowHeight
        drawRow.mColumnWidth = mColumnWidth
        drawRow.mSpace = mSpace
    }

    override fun initSchedule(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        setCurrentMonth(8)
        var dateList = ArrayList<String>()
        dateList.add("19 周一")
        dateList.add("20 周二")
        dateList.add("21 周三")
        dateList.add("22 周四")
        dateList.add("23 周五")
        dateList.add("24 周六")
        dateList.add("25 周日")
        setDateList(dateList)
    }

}
