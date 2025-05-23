package com.vinmahob.presentation.ui

data class ClockUiState(
    val seconds: LampState = LampState.O,
    val fiveHours: List<LampState> = emptyList(),
    val oneHour: List<LampState> = emptyList(),
    val fiveMinutes: List<LampState> = emptyList(),
    val oneMinute: List<LampState> = emptyList(),
    val errorMessage: String? = null // For displaying errors
)

enum class LampState {
    R, Y, O
}