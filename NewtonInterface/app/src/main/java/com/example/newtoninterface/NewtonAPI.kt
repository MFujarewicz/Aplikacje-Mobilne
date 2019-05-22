package com.example.newtoninterface

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewtonAPI {

    @GET("/{operation}/{expression}")
    fun getResult(@Path("operation") operation: String, @Path("expression") expression: String) : Call<NewtonData>
}

data class NewtonData(
    val operation: String,
    val expression: String,
    val result: Any
)