package com.example.stopwatch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.stopwatch.enity.ElapsedTimeCalculator
import com.example.stopwatch.enity.StopwatchListOrchestrator
import com.example.stopwatch.enity.StopwatchStateCalculator
import com.example.stopwatch.enity.StopwatchStateHolder
import com.example.stopwatch.model.TimestampProvider
import com.example.stopwatch.utils.TimestampMillisecondsFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainActivityViewModel : ViewModel() {

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )

    val ticker: LiveData<String> = stopwatchListOrchestrator.ticker.asLiveData()

    fun stopWatchStart() {
        stopwatchListOrchestrator.start()
    }

    fun stopWatchPause() {
        stopwatchListOrchestrator.pause()
    }

    fun stopWatchStop() {
        stopwatchListOrchestrator.stop()
    }
}