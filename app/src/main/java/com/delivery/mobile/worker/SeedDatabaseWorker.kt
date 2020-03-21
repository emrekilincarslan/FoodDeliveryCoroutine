package com.delivery.mobile.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.delivery.mobile.data.ApplicationDatabase
import com.delivery.mobile.restaurants.data.Restaurant
import com.delivery.mobile.util.DATA_FILENAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SeedDatabaseWorker@Inject constructor(
        var appDatabase:ApplicationDatabase,
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO) {

            try {
                applicationContext.assets.open(DATA_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<Restaurant>>() {}.type
                        val list: List<Restaurant> = Gson().fromJson(jsonReader, type)
                        appDatabase.restaurantsDao().insertAll(list)
                        Result.success()
                    }
                }
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}