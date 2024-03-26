package com.example.elixir.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.elixir.data.model.Exercise
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.elixir.data.model.Exercises

class ExerciseTrackingViewModel : ViewModel() {
    val exercise : MutableState<Exercise?> = mutableStateOf(null)

    fun setExercise(newExercise: Exercise) {
        exercise.value = newExercise
    }
}