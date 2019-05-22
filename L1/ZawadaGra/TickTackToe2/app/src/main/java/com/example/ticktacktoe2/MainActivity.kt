package com.example.ticktacktoe2

import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val width = Resources.getSystem().displayMetrics.widthPixels
        val heigth = Resources.getSystem().displayMetrics.heightPixels

        findViewById<TextView>(R.id.textView).text = "$heigth/$width"

        var buttonSize = 0
        if(heigth>width)
            buttonSize = width/5
        else
            buttonSize = heigth/5





        findViewById<Button>(R.id.button33).layoutParams.height = buttonSize
        findViewById<Button>(R.id.button33).layoutParams.width = buttonSize


    }


    fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density.toInt())
}
