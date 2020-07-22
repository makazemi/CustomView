package com.maryam.customfancontroller

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat


class ClippedGradientView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val path = Path()
    private val startX=0f
    private val startY=0f
    private var height=getHeight().toFloat()
    private var width=getWidth().toFloat()

    private val startColor=ContextCompat.getColor(context,R.color.colorStartGradientGrey)
    private val endColor=ContextCompat.getColor(context,R.color.colorEndGradientGrey)

    private val paint = Paint().apply {
        isAntiAlias = true
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        height=h.toFloat()
        width=w.toFloat()
        paint.shader=LinearGradient(startX,startY,width,height,startColor,endColor, Shader.TileMode.REPEAT)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawClipPath()
        canvas.clipPath(path)
        canvas.drawRect(0f,0f,width,height,paint)
    }

    private fun drawClipPath(){
        path.fillType= Path.FillType.EVEN_ODD
        path.lineTo(width,startY)
        path.lineTo(width,height)
        path.lineTo(0.75f*width,height)
        path.lineTo(startX,startY)
    }

}