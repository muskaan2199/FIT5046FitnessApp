package com.example.elixir

import com.example.elixir.data.ExerciseApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val api:ExerciseApi = Retrofit.Builder()
        //converts json to data class
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(ExerciseApi.BASE_URL)
        .client(client)
        .build()
        .create(ExerciseApi::class.java)
}