package com.example.stopwatch.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.stopwatch.databinding.ActivityMainBinding
import com.example.stopwatch.enity.ElapsedTimeCalculator
import com.example.stopwatch.enity.StopwatchListOrchestrator
import com.example.stopwatch.enity.StopwatchStateCalculator
import com.example.stopwatch.enity.StopwatchStateHolder
import com.example.stopwatch.model.TimestampProvider
import com.example.stopwatch.utils.TimestampMillisecondsFormatter
import com.example.stopwatch.viewModel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainActivityViewModel: MainActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivityViewModel.ticker.observe(this) {
            binding.textTime.text = it
        }

        with(binding) {
            buttonStart.setOnClickListener {
                mainActivityViewModel.stopWatchStart()
            }
            buttonPause.setOnClickListener {
                mainActivityViewModel.stopWatchPause()
            }
            buttonStop.setOnClickListener {
                mainActivityViewModel.stopWatchStop()
            }
        }
    }
}
