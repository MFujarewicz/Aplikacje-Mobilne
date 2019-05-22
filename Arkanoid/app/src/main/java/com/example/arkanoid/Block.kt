package com.example.arkanoid

import android.content.Context
import android.graphics.RectF

class Block(context: Context, val column: Int, val row: Int, private val screenX: Int, private val screenY: Int) {

    val width = screenX/10f
    val height = screenY/30f
    val spaceX = (screenX-6*width)/7
    val spaceY = spaceX;

    val offset =  (width+spaceX)/2





    val rect = RectF(spaceX*(column+1)+width*column+offset, spaceY*(row+1)+height*row, spaceX*(column+1)+width*column+width+offset, height+spaceY*(row+1)+height*row)



}