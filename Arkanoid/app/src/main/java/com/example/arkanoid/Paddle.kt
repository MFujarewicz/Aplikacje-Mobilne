package com.example.arkanoid

import android.content.Context
import android.graphics.RectF

class Paddle(context: Context, private val screenX: Int, private val screenY: Int) {

    val width = screenX/3f
    val height = screenY/30f

    var rect = RectF(screenX/2f-width/2f, screenY-height, screenX/2f+width/2f, screenY.toFloat())



    fun move(x: Float) {
        rect.left = x-width/2
        rect.right = x+width/2

        if (rect.left<0){
            move(width/2)
        }

        if (rect.right>screenX){
            move(screenX-width/2)
        }

    }
}