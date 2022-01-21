package com.example.workmanagerexemple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workmanagerexemple.databinding.ActivityMainBinding
import com.example.workmanagerexemple.room.GeoInfo
import com.example.workmanagerexemple.room.GeoInfoDatabase
import com.example.workmanagerexemple.service.SimpleWork

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener { SimpleWork.startWork(this) }
        binding.button2.setOnClickListener { SimpleWork.cancelWork(this) }

    }
}