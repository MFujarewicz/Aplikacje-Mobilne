package com.example.arkanoid

import android.content.Context
import android.content.SharedPreferences
import android.graphics.*
import android.os.Parcelable
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import org.json.JSONObject
import org.json.JSONStringer
import kotlin.math.abs
import kotlin.math.log
import kotlin.math.min

class GameView(context: Context, private val size: Point) : SurfaceView(context), Runnable {


    private val gameThread = Thread(this)

    private var playing = false
    private var paused = true

    var saved = false;


    var level = 0;

    private var canvas: Canvas = Canvas()
    private val paint: Paint = Paint()

    private var paddle: Paddle = Paddle(context, size.x, size.y)
    private var blocks = ArrayList<Block>()
    private var ball: Ball = Ball(context, size.x, size.y)

    private var score = 0
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "Arkanoid",
        Context.MODE_PRIVATE
    )

    private var hiScore = prefs.getInt("highScore", 0)


    private fun prepareLevel() {

        if (level > 10) level--;

        for (column in 0..4) {
            for (row in 1..2 + level) {
                blocks.add(Block(context, column, row, size.x, size.y))
            }
        }

    }

    override fun run() {
        var fps: Long = 0

        while (playing) {
            val startFrameTime = System.currentTimeMillis()

            if (!paused) {
                update(fps)
            }

            draw()

            val timeThisFrame = System.currentTimeMillis() - startFrameTime

            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame
            }

        }
    }

    private fun update(fps: Long) {


        ball.update(fps)

        if (ball.velocityY > 0 && ball.rect.intersect(paddle.rect)) {
            ball.paddleHit(paddle)
            if (blocks.isEmpty()) {
                level++;
                prepareLevel()
                ball.totalVelocity += 150;
            }

        }

        if (ball.ballLost) {
            lost()
        }

        for (block in blocks) {
            if (block.rect.intersect(ball.rect)) {

                blocks.remove(block)
                score++;

                if (score > hiScore) {
                    hiScore = score

                    prefs.edit().putInt("highScore", hiScore).apply()
                }


                var diffY = min(abs(ball.rect.bottom - block.rect.top), abs(ball.rect.top - block.rect.bottom))
                var diffX = min(abs(ball.rect.right - block.rect.left), abs(ball.rect.left - block.rect.right))

                if (diffX < diffY) ball.velocityX *= -1 else ball.velocityY *= -1

                break


            }
        }

    }

    private fun lost() {
        paused = true

        blocks.removeAll(blocks)
        level = 0
        prepareLevel()
        ball = Ball(context, size.x, size.y)
        score = 0

    }

    private fun draw() {

        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()

            canvas.drawColor(Color.BLACK)






            paint.color = Color.WHITE
            paint.textAlign = Paint.Align.CENTER

            paint.color = Color.GRAY
            paint.textSize = 100f
            canvas.drawText("$hiScore", size.x / 2f, size.y / 1.6f, paint)

            paint.color = Color.WHITE
            paint.textSize = 400f
            canvas.drawText("$score", size.x / 2f, size.y / 1.2f, paint)


            canvas.drawRect(paddle.rect, paint)
            canvas.drawCircle(ball.x, ball.y, ball.radius, paint)



            for (block in blocks) {
                canvas.drawRect(block.rect, paint)
            }

            holder.unlockCanvasAndPost(canvas)


        }

    }

    fun pause() {

        playing = false
        paused = true
        gameThread.join()


    }


    fun resume() {
        playing = true
        paused = false





        gameThread.start()
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        paused = false

        paddle.move(event!!.x)

        return true
    }
}


