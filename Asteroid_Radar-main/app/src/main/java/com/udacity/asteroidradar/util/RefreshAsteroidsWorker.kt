package com.udacity.asteroidradar.util

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.JsonParser
import com.udacity.asteroidradar.api.AsteroidRepositoryService
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDao
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.DayProvider
import com.udacity.asteroidradar.database.PictureDao
import org.json.JSONObject
import retrofit2.HttpException

class RefreshAsteroidsWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    private val asteroidDao: AsteroidDao by lazy {
        AsteroidDatabase.getDatabase(applicationContext).asteroidDao()
    }

    private val pictureDao: PictureDao by lazy {
        AsteroidDatabase.getDatabase(applicationContext).pictureOfDayDao()
    }


    private val repository: AsteroidRepositoryService by lazy { AsteroidRepositoryService() }

    companion object {
        const val WORK_NAME = "RefreshAsteroidsWorker"
    }

    override suspend fun doWork(): Result {

        return try {
            val response = repository.service.getAsteroids(
                DayProvider.getToday(),
                DayProvider.getSevenDaysLater()
            )
            val gson = JsonParser().parse(response.toString()).asJsonObject

            val jo2 = JSONObject(gson.toString())
            val asteroids = parseAsteroidsJsonResult(jo2)

            asteroidDao.insert(asteroids)

            val picture = repository.service.getPicture()
            pictureDao.insert(picture)

            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}