package com.example.app1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.widget.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private var score = 0L
private var autoMultiplier = 1L
private var manualMultiplier = 1L
private var autoClickDelay = 200
private var autoClickCount = 0
private var clicksPerSecond = 0L

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.score).setTextSize(50F)
        findViewById<TextView>(R.id.score).gravity = Gravity.CENTER



        GlobalScope.launch {
            while (true){
                updateScore()
                delay(10)
            }
        }

        GlobalScope.launch {
            var old = 0L
            var new = 0L;

            while (true){
                old = score;
                delay(1000)
                new = score;
                clicksPerSecond = (new-old);

                findViewById<TextView>(R.id.clicksPerSecond).text = "$clicksPerSecond klikosekundy"

                if(clicksPerSecond>=0){
                    findViewById<ImageView>(R.id.star).scaleX = clicksPerSecond.toFloat()/100;
                    findViewById<ImageView>(R.id.star).scaleY = clicksPerSecond.toFloat()/100;
                }
            }
        }

        GlobalScope.launch {
            while (true){
                if (findViewById<Switch>(R.id.autoSwitch1).isChecked){
                    score+=1*autoMultiplier
                }
                delay(autoClickDelay.toLong())
                if (findViewById<Switch>(R.id.autoSwitch2).isChecked){
                    score+=1*autoMultiplier
                }
                delay(autoClickDelay.toLong())
                if (findViewById<Switch>(R.id.autoSwitch3).isChecked){
                    score+=1*autoMultiplier
                }
                delay(autoClickDelay.toLong())
                if (findViewById<Switch>(R.id.autoSwitch4).isChecked){
                    score+=1*autoMultiplier
                }
                delay(autoClickDelay.toLong())
                if (findViewById<Switch>(R.id.autoSwitch5).isChecked){
                    score+=1*autoMultiplier
                }
                delay(autoClickDelay.toLong())
            }

        }

    }

    fun button1(view: View){
        score+=1*manualMultiplier;


    }

    fun upgradeClickPower(view: View){
        if (score>=50){
            score-=50
            manualMultiplier++
            Toast.makeText(this, "Modernizacja dała owoc", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Lud nie gotowy na nową technologię", Toast.LENGTH_SHORT).show()
        }
    }

    fun autoClickUpgrade(view: View){
        if (score >= 10000){
            score-=10000
            autoMultiplier*=2;
            Toast.makeText(this, "Dalej do pracy budować wspólne dobro", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Efektywność utrzymana na zadowalającym poziomie", Toast.LENGTH_LONG).show()

        }
    }

    fun upgradeAutoclick(view: View){
        if (score>=1000){
            if (autoClickCount == 0){
                findViewById<Button>(R.id.button3).text = "Samoklik 1/5 [1000]"
                findViewById<Switch>(R.id.autoSwitch1).visibility = Switch.VISIBLE
                Toast.makeText(this, "Automatyzacja to cel każedego rozwiniętego społeczeństwa", Toast.LENGTH_LONG).show()
            }
            if (autoClickCount == 1){
                findViewById<Button>(R.id.button3).text = "Samoklik 2/5 [1000]"
                findViewById<Switch>(R.id.autoSwitch2).visibility = Switch.VISIBLE
                Toast.makeText(this, "Automatyzacja to cel każedego rozwiniętego społeczeństwa", Toast.LENGTH_LONG).show()
            }
            if (autoClickCount == 2){
                findViewById<Button>(R.id.button3).text = "Samoklik 3/5 [1000]"
                findViewById<Switch>(R.id.autoSwitch3).visibility = Switch.VISIBLE
                Toast.makeText(this, "Automatyzacja to cel każedego rozwiniętego społeczeństwa", Toast.LENGTH_LONG).show()
            }
            if (autoClickCount == 3){
                findViewById<Button>(R.id.button3).text = "Samoklik 4/5 [1000]"
                findViewById<Switch>(R.id.autoSwitch4).visibility = Switch.VISIBLE
                Toast.makeText(this, "Automatyzacja to cel każedego rozwiniętego społeczeństwa", Toast.LENGTH_LONG).show()
            }
            if (autoClickCount == 4){
                findViewById<Button>(R.id.button3).text = "Samoklik 5/5 [1000]"
                findViewById<Switch>(R.id.autoSwitch5).visibility = Switch.VISIBLE
                Toast.makeText(this, "Automatyzacja to cel każedego rozwiniętego społeczeństwa", Toast.LENGTH_LONG).show()
            }
            if (autoClickCount >= 5){
                Toast.makeText(this, "Nastał kres możliwości narodu, przyszłość należy do naszych potomków", Toast.LENGTH_LONG).show()
                score +=1000
            }
            score-=1000
            autoClickCount++


        }else{
            Toast.makeText(this, "Osczędnością i pracą kaczki się bogacą", Toast.LENGTH_LONG).show()

        }
    }

    fun updateScore(){
        findViewById<TextView>(R.id.score).text = "$score"
    }
}
