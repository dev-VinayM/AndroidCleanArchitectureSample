package com.vinmahob.presentation.ui

sealed interface BerlinClockViewIntent {
    data class OnButtonClicked(val input: String) : BerlinClockViewIntent
}