package com.example.presentation.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class HomeViewModel
    @Inject
    constructor() : ViewModel() {
        private val _state = MutableStateFlow(HomeState(0))
        val state: StateFlow<HomeState> = _state.asStateFlow()
    }
