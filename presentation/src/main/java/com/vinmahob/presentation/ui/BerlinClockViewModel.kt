package com.berlin.uhr.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berlin.uhr.domain.BerlinClockUseCase
import com.berlin.uhr.domain.model.BerlinClockValue
import com.vinmahob.presentation.ui.BerlinClockViewIntent
import com.vinmahob.presentation.ui.ClockUiState
import com.vinmahob.presentation.ui.LampState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BerlinClockViewModel @Inject constructor(
    private val useCase: BerlinClockUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(BerlinClockValue())
    val viewState: StateFlow<BerlinClockValue> = _viewState.asStateFlow()

    fun sendIntent(intent: BerlinClockViewIntent) = viewModelScope.launch {
        when (intent) {
            is BerlinClockViewIntent.OnButtonClicked -> updateViewState(useCase.convert(intent.input))
        }
    }

    private fun updateViewState(newViewState: BerlinClockValue) {
        _viewState.value = newViewState
    }
}
