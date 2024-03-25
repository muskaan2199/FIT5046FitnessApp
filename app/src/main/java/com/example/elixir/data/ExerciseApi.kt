package com.example.elixir.data

import androidx.compose.ui.geometry.Offset
import com.example.elixir.data.model.Exercises
import retrofit2.http.GET
import retrofit2.http.Query

interface ExerciseApi {

    @GET("exercises")
    suspend fun getExerciseList(
        @Query("X-Api-Key") apiKey:String,
        @Query("offset") offset: Int,
        @Query("muscle") muscle:String
    ): Exercises

    companion object {
        const val BASE_URL = "https://api.api-ninjas.com/v1/"
    }
}