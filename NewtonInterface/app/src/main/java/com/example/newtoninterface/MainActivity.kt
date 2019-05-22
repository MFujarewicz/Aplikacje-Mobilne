package com.example.newtoninterface

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofit = Retrofit.Builder()
            .baseUrl("http://156.17.7.48:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val operations = arrayOf("simplify", "factor", "derive", "integrate", "zeroes", "tangent", "area", "cos", "sin",
            "tan", "arccos", "arcsin", "arctan", "abs", "log")

        val buttons = arrayOf(simplifyButton, factorButton, deriveButton, integrateButton, find0Button, tangentButton,
            areaUnderCurveButton, cosineButton, sineButton, tangentButton, invertedCosineButton, invertedSineButton,
            invertedTangentButton, absoluteValueButton, logarithmButton)

        for (i in 0..14){
            buttons[i].setOnClickListener {
                getResultFromNewtonAPI(operations[i])
            }
        }
    }

    fun getResultFromNewtonAPI(operation: String){

        val expression = inputEditText.text.toString()

        val newton = retrofit.create(NewtonAPI::class.java)
        val call = newton.getResult(operation, expression)
        call.enqueue(object : Callback<NewtonData>{
            override fun onFailure(call: Call<NewtonData>, t: Throwable) {
                Log.d("API", "accessing Newton API failed")
            }

            override fun onResponse(call: Call<NewtonData>, response: Response<NewtonData>) {
                val body = response.body()
                resultTextView.text = body?.result.toString()
            }
        })
    }
}


