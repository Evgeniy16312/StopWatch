package com.example.stopwatch.di

import com.example.stopwatch.viewModel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainActivityViewModel = module {
    viewModel { MainActivityViewModel() }
}