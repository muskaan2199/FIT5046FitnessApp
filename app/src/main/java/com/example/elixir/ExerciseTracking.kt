package com.example.elixir

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.elixir.presentation.ExerciseTrackingViewModel
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Composable
fun ExerciseTracking(exerciseTrackingViewModel: ExerciseTrackingViewModel) {

    val exercise = exerciseTrackingViewModel.exercise.value
   var time by remember {
       mutableStateOf(0L)
   }

    var isRunning by remember {
        mutableStateOf(false)
    }

    var startTime by remember {
        mutableStateOf(0L)
    }

    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        if (exercise != null) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp)),

                painter = painterResource(id = R.drawable.monday),
                contentDescription = exercise.name,
                contentScale = ContentScale.Crop,

                )
        }
        Text(
            text = formatTime(timeMi = time),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(9.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Row {
            Button(onClick = { if(isRunning) {
                isRunning = false
            } else {
                startTime = System.currentTimeMillis() - time
                isRunning = true
                keyboardController?.hide()
            }
            }, modifier = Modifier.weight(1f)) {
                Text(text = if(isRunning) "Pause" else "Start", color = Color.White)

            }

            Spacer(modifier = Modifier.width(16.dp))
            
            Button(onClick = {
                time = 0
                isRunning = false
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Reset", color = Color.White)
            }
        }
    }
    LaunchedEffect(isRunning) {
        while(isRunning) {
            delay(1000)
            time = System.currentTimeMillis() - startTime
        }
    }
}

@Composable
fun formatTime(timeMi: Long) : String {
    val hours = TimeUnit.MILLISECONDS.toHours(timeMi)
    val min = TimeUnit.MILLISECONDS.toMinutes(timeMi) % 60
    val sec = TimeUnit.MILLISECONDS.toSeconds(timeMi) % 60

    return String.format("%02d:%02d:%02d", hours, min, sec)
}

@Preview
@Composable
fun ExerciseTrackingPreview(exerciseTrackingViewModel: ExerciseTrackingViewModel)
{
    ExerciseTracking(exerciseTrackingViewModel)
}