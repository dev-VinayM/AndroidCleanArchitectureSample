import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.berlin.uhr.ui.BerlinClockViewModel
import com.vinmahob.presentation.ui.BerlinClockViewIntent
import com.vinmahob.presentation.ui.ClockUiState

@Composable
fun BerlinerClockScreenWithInput(
    viewModel: BerlinClockViewModel
) {
    var timeInput by rememberSaveable { mutableStateOf("12:56:01") }
    val clockUiState by remember { mutableStateOf(ClockUiState()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp, Alignment.CenterVertically
        )
    ) {
        OutlinedTextField(
            value = timeInput,
            onValueChange = { newTime ->
                timeInput = newTime
            },
            label = { Text("Enter Time (HH:MM:SS)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Button(
            onClick = {
                viewModel.sendIntent(BerlinClockViewIntent.OnButtonClicked(timeInput))
            }, modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text("Convert to Berlin Clock")
        }

        if (clockUiState.errorMessage != null) {
            Text(
                text = clockUiState.errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.titleMedium
            )
        } else {
            BerlinClockDisplay(clockUiState)
        }
    }
}

@Composable
fun BerlinClockDisplay(clockUiState: ClockUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(clockUiState.seconds.name)
        Text(clockUiState.fiveHours.joinToString("") { it.name.first().toString() })
        Text(clockUiState.oneHour.joinToString("") { it.name.first().toString() })
        Text(clockUiState.fiveMinutes.joinToString("") { it.name.first().toString() })
        Text(clockUiState.oneMinute.joinToString("") { it.name.first().toString() }
        )
    }
}
