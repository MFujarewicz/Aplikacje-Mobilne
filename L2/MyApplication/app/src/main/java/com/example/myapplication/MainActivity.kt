package com.example.myapplication

import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var turn = "X";


    override fun onCreate(savedInstanceState: Bundle?) {

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttons = listOf<Button>(
            button11, button12, button13, button14, button15,
            button21, button22, button23, button24, button25,
            button31, button32, button33, button34, button35,
            button41, button42, button43, button44, button45,
            button51, button52, button53, button54, button55
        )



        val width = Resources.getSystem().displayMetrics.widthPixels
        val heigth = Resources.getSystem().displayMetrics.heightPixels

        var x = 0;
        if (heigth > width)
            x = width / 6;
        else
            x = heigth / 6;



        for (b in buttons) {
            b.layoutParams.width = x
            b.layoutParams.height = x
            b.textSize = (x / Resources.getSystem().displayMetrics.density) / 2f
            b.setOnClickListener {

                if (findViewById<TextView>(R.id.turn).text.equals("DRAW") || findViewById<TextView>(R.id.turn).text.equals("WINNER X") || findViewById<TextView>(R.id.turn).text.equals("WINNER O")){
                    reset();
                }else{

                if (b.text.equals("")) {
                    b.text = turn;
                    nextTurn();
                }
                }
            }
        }
        findViewById<TextView>(R.id.turn).textSize = (x / Resources.getSystem().displayMetrics.density) / 1.5f

    }

    fun nextTurn() {
        turn = if (turn.equals("X"))
            "O"
        else
            "X"
        findViewById<TextView>(R.id.turn).text = turn;
        checkForWinner();
    }


    fun checkForWinner() {

        val row1 = listOf<Button>(
            button11, button12, button13, button14, button15
        )
        val row2 = listOf<Button>(
            button21, button22, button23, button24, button25
        )
        val row3 = listOf<Button>(
            button31, button32, button33, button34, button35
        )
        val row4 = listOf<Button>(
            button41, button42, button43, button44, button45
        )
        val row5 = listOf<Button>(
            button51, button52, button53, button54, button55
        )

        val column1 = listOf<Button>(
            button11, button21, button31, button41, button51
        )
        val column2 = listOf<Button>(
            button12, button22, button32, button42, button52
        )
        val column3 = listOf<Button>(
            button13, button23, button33, button43, button53
        )
        val column4 = listOf<Button>(
            button14, button24, button34, button44, button54
        )
        val column5 = listOf<Button>(
            button15, button25, button35, button45, button55
        )

        val across1 = listOf<Button>(
            button11, button22, button33, button44, button55
        )
        val across2 = listOf<Button>(
            button51, button42, button33, button24, button15
        )

        val winningRows = listOf(
            row1, row2, row3, row4, row5, column1, column2, column3, column4, column5, across1, across2
        )

        var allSame = true
        var emptyFound = false;
        for (l in winningRows) {
            allSame = true
            if (l.first().text.equals("")) allSame = false;
            for (b in l) {
                if (b.text.equals("")) emptyFound = true
                if (b.text != l.first().text)
                    allSame = false
            }
            if (allSame) findViewById<TextView>(R.id.turn).text = "WINNER" +" "+ l.first().text
        }

        if (!emptyFound) findViewById<TextView>(R.id.turn).text = "DRAW"
    }

    fun reset(){
        val buttons = listOf<Button>(
            button11, button12, button13, button14, button15,
            button21, button22, button23, button24, button25,
            button31, button32, button33, button34, button35,
            button41, button42, button43, button44, button45,
            button51, button52, button53, button54, button55
        )

        buttons.map { it.text = "" }
        findViewById<TextView>(R.id.turn).text = turn;
    }
}
