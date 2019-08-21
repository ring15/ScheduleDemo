package com.founq.sdk.scheduledemo.widget

import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint

/**
 * Created by ring on 2019/8/21.
 */
class DrawCenterRect {

    var mWidth: Int = 0
    var mColumnHeight: Int = 0
    var mColumnWidth: Int = 0
    var mRowHeight: Int = 0
    var mSpace = 42
    var mStartPosition = 0
    var mRectPaint = Paint()
    var mLinePaint: Paint = Paint()
    var mTextPaint: TextPaint = TextPaint()

    init {
        mRectPaint.isAntiAlias = true
        mLinePaint.style = Paint.Style.FILL
        mLinePaint.color = Color.WHITE
        mTextPaint.textSize = 30F
        mTextPaint.textAlign = Paint.Align.CENTER
    }

    fun draw(canvas: Canvas?, row: Int, startColumn: Int, endColumn: Int, color: Int, text :String) {
        mRectPaint.color = color
        val size = 7
        val width = (mWidth - mColumnWidth - mSpace) / size
        val rect = RectF((mColumnWidth + (row + 1) * mSpace / size + row * width).toFloat(),
                mStartPosition.toFloat() + mRowHeight.toFloat() + startColumn * mColumnHeight + (startColumn + 1) * mSpace / size,
                mColumnWidth.toFloat() + (row + 1) * width + (row + 1) * mSpace / size,
                mStartPosition.toFloat() + mRowHeight.toFloat() + endColumn * (mColumnHeight + mSpace / size))
//        canvas?.drawRect(, mRectPaint)
        canvas?.drawRoundRect(rect, 10f, 10f, mRectPaint)

        canvas?.save()
        val myStaticLayout = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
             StaticLayout.Builder.obtain(text,0, text.length, mTextPaint, width)
                     .setAlignment(Layout.Alignment.ALIGN_NORMAL)
                     .setIncludePad (false)
                     .build()
        } else {
            StaticLayout(text , mTextPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false)
        }

        canvas?.translate(mColumnWidth.toFloat() + row * width + row * mSpace / size + width / 2,
                mStartPosition.toFloat() + mRowHeight.toFloat() + startColumn * mColumnHeight + (startColumn + 1) * mSpace / size+ (mColumnHeight * (endColumn - startColumn)+(endColumn - startColumn - 1) * mSpace / size - myStaticLayout.height) / 2)
        myStaticLayout.draw(canvas)
        canvas?.restore()

        //竖向间距
        canvas?.drawRect(mColumnWidth.toFloat() + row * width + row * mSpace / size,
                mStartPosition.toFloat() + mRowHeight.toFloat() + startColumn * mColumnHeight + (startColumn + 1) * mSpace / size,
                mColumnWidth.toFloat() + row * width + (row + 1) * mSpace / size,
                mStartPosition.toFloat() + mRowHeight.toFloat() + endColumn * (mColumnHeight + mSpace / size), mLinePaint)

        //横向间距
        canvas?.drawRect((mColumnWidth + (row + 1) * mSpace / size + row * width).toFloat(),
                mStartPosition.toFloat() + mRowHeight.toFloat() + startColumn * mColumnHeight + startColumn * mSpace / size,
                mColumnWidth.toFloat() + (row + 1) * width + (row + 1) * mSpace / size,
                mStartPosition.toFloat() + mRowHeight.toFloat() + startColumn * mColumnHeight + (startColumn + 1) * mSpace / size, mLinePaint)

    }
}