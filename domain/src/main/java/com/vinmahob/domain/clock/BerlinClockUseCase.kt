package com.berlin.uhr.domain

import com.berlin.uhr.domain.model.BerlinClockValue
import com.berlin.uhr.domain.model.Lamps
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*


class BerlinClockUseCase {
    fun convert(timeString: String): BerlinClockValue {
        val parts = timeString.split(":")
        if (parts.size != 3) {
            return BerlinClockValue(errorMessage = "Invalid time format. Use HH:MM:SS")
        }

        val hours = parts[0].toIntOrNull()
        val minutes = parts[1].toIntOrNull()
        val seconds = parts[2].toIntOrNull()

        if (hours == null || minutes == null || seconds == null ||
            hours !in 0..23 || minutes !in 0..59 || seconds !in 0..59
        ) {
            return BerlinClockValue(errorMessage = "Invalid time value. Hours (0-23), Minutes (0-59), Seconds (0-59).")
        } else {
            return BerlinClockValue(
                seconds = if (seconds % 2 == 0) Lamps.RED else Lamps.OFF,
                fiveHours = calculateLampStates(hours / 5, 4, Lamps.RED, Lamps.OFF),
                oneHour = calculateLampStates(hours % 5, 4, Lamps.RED, Lamps.OFF),
                fiveMinutes = calculateFiveMinuteLampStates(
                    minutes / 5,
                    11,
                    Lamps.YELLOW,
                    Lamps.OFF
                ),
                oneMinute = calculateLampStates(minutes % 5, 4, Lamps.YELLOW, Lamps.OFF)
            )
        }
    }

    private fun calculateLampStates(
        activeCount: Int,
        totalLamps: Int,
        activeState: Lamps,
        inactiveState: Lamps
    ): List<Lamps> {
        return List(activeCount) { activeState } + List(totalLamps - activeCount) { inactiveState }
    }

    private fun calculateFiveMinuteLampStates(
        activeMinutesInFiveBlock: Int,
        totalLamps: Int,
        activeYellowState: Lamps,
        inactiveState: Lamps
    ): List<Lamps> {
        val redState = Lamps.RED
        return List(totalLamps) { index ->
            if (index < activeMinutesInFiveBlock) {
                if ((index + 1) % 3 == 0) redState else activeYellowState
            } else {
                inactiveState
            }
        }
    }
}