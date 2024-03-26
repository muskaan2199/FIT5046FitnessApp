package com.example.elixir


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.automirrored.outlined.DirectionsRun
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SportsGymnastics
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.elixir.data.model.Exercise
import com.example.elixir.presentation.ExerciseTrackingViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Exercise(
    exercise: Exercise,
    navHostController : NavHostController,
    exerciseTrackingViewModel: ExerciseTrackingViewModel) {

        val imageState = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://www.chiefsthreads.com/uploads/1/2/7/4/12743439/s582883740420043145_p457_i1_w1502.jpeg")
                .size(Size.ORIGINAL).build()
        ).state
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .height(100.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
        )   {
            if (imageState is AsyncImagePainter.State.Error) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            }
            if (imageState is AsyncImagePainter.State.Success) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    painter = imageState.painter,
                    contentDescription = exercise.name,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(6.dp))
            Column {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "${exercise.name}  ",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold

                )
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "Difficulty: ${exercise.difficulty}",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold

                )

                Row(modifier = Modifier.padding(8.dp)) {

                    Spacer(Modifier.weight(1f))
                    Button(onClick = {
                        exerciseTrackingViewModel.setExercise(exercise)
                        navHostController.navigate("workout_track")}) {
                        Text("Start")
                    }
                }


            }


        }
    }


