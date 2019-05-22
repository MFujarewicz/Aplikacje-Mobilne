package com.example.arkanoid

import android.graphics.Color
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View

class GameActivity : AppCompatActivity() {

    private var gameView: GameView? = null;





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        window.statusBarColor = Color.WHITE;

        val display = windowManager.defaultDisplay

        val size = Point()
        display.getSize(size)
        gameView = GameView(this, size)
        setContentView(gameView)
    }

    override fun onResume() {
        super.onResume()

        gameView?.resume()
    }



    override fun onPause() {
        super.onPause()

        gameView?.pause()
    }

}
