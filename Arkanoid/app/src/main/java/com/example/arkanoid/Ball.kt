package com.example.arkanoid

import android.content.Context
import android.graphics.RectF
import java.util.*
import kotlin.math.abs
import kotlin.math.sqrt

class Ball(context: Context, private val screenX: Int, private val screenY: Int) {



    val radius = screenY/100f

    var x = screenX/2f
    var y = screenY/1.31f



    var ballLost= false;

    var velocityX = 0f

    var velocityY = 400f;
    var totalVelocity = 1500f
    var rect = RectF(x-radius, y-radius, x+radius, y+radius)


    val rectWidth = rect.right-rect.left;


    fun update(fps: Long){

        if (y>screenY){
            ballLost=true;
        }

        if(x-radius<0 && velocityX<0 || x+radius>screenX && velocityX>0) velocityX*= -1;
        if(y-radius<0 && velocityY<0) velocityY*= -1;


        x+=velocityX/fps
        y+=velocityY/fps

        rect.set(x-radius, y-radius, x+radius, y+radius)
    }

    fun paddleHit(paddle: Paddle) {

        var d = paddle.rect.width()+rect.width()

        d*=1.4f

        var fromCenter = (x-paddle.rect.centerX())/(d/2)

        velocityX = fromCenter*totalVelocity;


        velocityY = -sqrt(abs(totalVelocity*totalVelocity-velocityX*velocityX))






    }
}