package com.berlin.uhr.domain.model

data class BerlinClockValue(
    val seconds: Lamps = Lamps.OFF,
    val fiveHours: List<Lamps> = emptyList(),
    val oneHour: List<Lamps> = emptyList(),
    val fiveMinutes: List<Lamps> = emptyList(),
    val oneMinute: List<Lamps> = emptyList(),
    val errorMessage: String? = null // For displaying errors
)
