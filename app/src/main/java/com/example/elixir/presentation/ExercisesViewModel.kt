package com.example.elixir.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elixir.data.ExercisesRepository
import com.example.elixir.data.Result
import com.example.elixir.data.model.Exercise
import com.example.elixir.data.model.Exercises
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExercisesViewModel(
    private val exercisesRepository: ExercisesRepository
) : ViewModel() {
    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises = _exercises.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            exercisesRepository.getExercisesList().collectLatest { result ->
                when(result) {
                    is Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let { exercises ->
                            _exercises.update { exercises }
                        }
                    }
                }
            }
        }
    }
}