package com.example.elixir.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.elixir.data.model.Exercise
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class ExercisesRepositoryImpl(
    private val api: ExerciseApi
): ExercisesRepository {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getExercisesList(): Flow<Result<List<Exercise>>> {
        //sequence of operations
        return flow {
            val exercisesFromApi = try {
                api.getExerciseList("sTvPL2km3YH2ByzlXx6HNw==TctLsQSeuBZfGKzd", 0, "biceps")

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading exercises"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading exercises"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading exercises"))
                return@flow
            }

            emit((Result.Success(exercisesFromApi)))
        }
    }
}