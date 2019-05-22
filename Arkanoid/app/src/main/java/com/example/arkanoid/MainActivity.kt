package com.example.arkanoid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        window.statusBarColor = Color.BLACK;
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar!!.hide()


        val prefs: SharedPreferences = getSharedPreferences(
            "Arkanoid",
            Context.MODE_PRIVATE
        )

        var hiScore = prefs.getInt("highScore", 0)

        highScoreTextView.text = "$hiScore"




        playButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)

            startActivity(intent)

        }

    }


    override fun onResume() {
        super.onResume()

        val prefs: SharedPreferences = getSharedPreferences(
            "Arkanoid",
            Context.MODE_PRIVATE
        )

        var hiScore = prefs.getInt("highScore", 0)

        highScoreTextView.text = "$hiScore"
    }
}
