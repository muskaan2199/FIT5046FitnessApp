package com.example.elixir.data

import com.example.elixir.data.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {
    suspend fun getExercisesList(): Flow<Result<List<Exercise>>>
}