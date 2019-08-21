package com.founq.sdk.scheduledemo.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.text.TextPaint
import android.text.Layout
import android.text.StaticLayout



/**
 * Created by ring on 2019/8/21.
 */
class DrawColumn {
    var mRowHeight: Int = 0
    var mColumnWidth: Int = 0
    var mColumnHeight: Int = 0
    var mRectPaint: Paint = Paint()
    var mLinePaint: Paint = Paint()
    var mTextPaint: TextPaint = TextPaint()
    var mSpace = 60
    var mStartPosition = 0

    init {
        mRectPaint.style = Paint.Style.FILL
        mRectPaint.color = Color.parseColor("#FFCCCECD")
        mTextPaint.textSize = 30F
        mTextPaint.textAlign = Paint.Align.CENTER
        mLinePaint.style = Paint.Style.FILL
        mLinePaint.color = Color.WHITE
    }

    fun draw(canvas: Canvas?) {
        val size = 10
        for (i: Int in 0 until size) {
            canvas?.drawRect(0f, mStartPosition.toFloat() + mRowHeight.toFloat() + i * mColumnHeight + (i + 1) * mSpace / size,
                    mColumnWidth.toFloat(), mStartPosition.toFloat() + mRowHeight.toFloat() + (i + 1) * (mColumnHeight + mSpace / size), mRectPaint)
            canvas?.drawRect(0f, mStartPosition.toFloat() + mRowHeight.toFloat() + i * mColumnHeight + i * mSpace / size,
                    mColumnWidth.toFloat(), mStartPosition.toFloat() + mRowHeight.toFloat() + i * mColumnHeight + (i + 1) * mSpace / size, mLinePaint)


            canvas?.save()
            val myStaticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                StaticLayout.Builder.obtain((i+1).toString(),0, (i+1).toString().length, mTextPaint, mColumnWidth)
                        .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                        .setIncludePad (false)
                        .build()
            } else {
                StaticLayout((i+1).toString() , mTextPaint, mColumnWidth, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false)
            }
            canvas?.translate(mColumnWidth.toFloat()/2,
                    mStartPosition.toFloat() + mRowHeight.toFloat() + i * mColumnHeight + (i + 1) * mSpace / size + (mColumnHeight - myStaticLayout.height) / 2 )
            myStaticLayout.draw(canvas)
            canvas?.restore()
        }
    }

    fun getHeight(): Int {
        return mColumnHeight * 10 + mSpace
    }
}