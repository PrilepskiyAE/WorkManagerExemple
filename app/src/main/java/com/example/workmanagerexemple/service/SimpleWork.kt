package com.example.workmanagerexemple.service

import android.content.Context
import android.widget.Toast
import androidx.work.*

import com.example.workmanagerexemple.room.GeoInfo
import com.example.workmanagerexemple.room.GeoInfoDatabase

class SimpleWork(private val context: Context,params: WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        val dao = GeoInfoDatabase.getDatabase(context).wordDao()
         dao.insert(GeoInfo(0, 37.419857, -122.078827, System.currentTimeMillis()))
        Toast.makeText(context, "Work in proccess", Toast.LENGTH_SHORT).show()
        return Result.success()
    }
    companion object {

        // 1. Метод для создание условий
        private fun createConstraints() = Constraints.Builder()
            // 1a. Условие подключения к Wi-Fi
            .setRequiredNetworkType(NetworkType.UNMETERED)
            // 1b. Подключения к зарядке
            .setRequiresCharging(true)
            .build()

        private const val SIMPLE_WORKER_TAG = "SimpleWorkerTag"
        // 1. Метод для создания OneTimeWorkRequest
        private fun createWorkRequest(data: Data): OneTimeWorkRequest {
            return OneTimeWorkRequest.Builder(SimpleWork::class.java)
                .setConstraints(createConstraints())
                .setInputData(data)
                .addTag(SIMPLE_WORKER_TAG)
                .build()
        }

        // 2. Метод для запуска
        fun startWork(context: Context) {
            val work = createWorkRequest(Data.EMPTY)
            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    SIMPLE_WORKER_TAG,
                    ExistingWorkPolicy.APPEND,
                    work
                )
            Toast.makeText(context, "Work in Start", Toast.LENGTH_SHORT).show()
        }
        // 3. Метод для остановки
        fun cancelWork(context: Context) {
            WorkManager.getInstance(context).cancelAllWorkByTag(SIMPLE_WORKER_TAG)
            Toast.makeText(context, "Work in Stop", Toast.LENGTH_SHORT).show()
        }
    }

}