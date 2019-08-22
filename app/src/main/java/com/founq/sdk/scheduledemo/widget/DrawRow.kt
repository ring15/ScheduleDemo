package com.founq.sdk.scheduledemo.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint

/**
 * Created by ring on 2019/8/21.
 */
class DrawRow {
    var mWidth: Int = 0
    var mHeight: Int = 0
    var mRowHeight: Int = 0
    var mColumnWidth: Int = 0
    var mSelectItem: Int = 0
    var mCurrentMonth: Int = 1
    var mDateList: List<String> = ArrayList()
    var mRectPaint: Paint = Paint()
    var mTextPaint: TextPaint = TextPaint()
    var mLinePaint: Paint = Paint()
    var mSpace = 42

    init {
        mRectPaint.style = Paint.Style.FILL
        mRectPaint.color = Color.parseColor("#FFC2BBBE")
        mTextPaint.textSize = 30F
        mTextPaint.textAlign = Paint.Align.CENTER
        mLinePaint.style = Paint.Style.FILL
        mLinePaint.color = Color.WHITE
    }

    fun draw(canvas: Canvas?) {
        canvas?.drawRect(0f, 0f, mColumnWidth.toFloat(), mRowHeight.toFloat(), mRectPaint)

        canvas?.save()
        val myStaticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StaticLayout.Builder.obtain(mCurrentMonth.toString() + "月",0, (mCurrentMonth.toString() + "月").length, mTextPaint, mColumnWidth)
                    .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                    .setIncludePad (false)
                    .build()
        } else {
            StaticLayout(mCurrentMonth.toString() + "月" , mTextPaint, mColumnWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false)
        }
        canvas?.translate(mColumnWidth.toFloat() / 2,
                (mRowHeight - myStaticLayout.height).toFloat() / 2)
        myStaticLayout.draw(canvas)
        canvas?.restore()

        val size = 7
        val width = (mWidth - mColumnWidth - mSpace) / size
        for (i: Int in 0 until size) {
            if (i == mSelectItem) {
                mRectPaint.color = Color.parseColor("#FF96F7EC")
            }
            canvas?.drawRect(mColumnWidth.toFloat() + i * width + (i + 1) * mSpace / size, 0f,
                    mColumnWidth.toFloat() + (i + 1) * width + (i + 1) * mSpace / size, mRowHeight.toFloat(), mRectPaint)
            canvas?.drawRect(mColumnWidth.toFloat() + i * width + i * mSpace / size, 0f,
                    mColumnWidth.toFloat() + i * width + (i + 1) * mSpace / size, mRowHeight.toFloat(), mLinePaint)
            mRectPaint.color = Color.parseColor("#FFC2BBBE")

            if (mDateList.size > i) {
                canvas?.save()
                val myStaticLayout1 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    StaticLayout.Builder.obtain(mDateList[i],0, mDateList[i].length, mTextPaint, width)
                            .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                            .setIncludePad (false)
                            .build()
                } else {
                    StaticLayout(mDateList[i] , mTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false)
                }
                canvas?.translate(mColumnWidth.toFloat() + i * width + (i+1) * mSpace / size + width / 2,
                        (mRowHeight - myStaticLayout1.height).toFloat() / 2)
                myStaticLayout1.draw(canvas)
                canvas?.restore()
            }
        }
    }
}