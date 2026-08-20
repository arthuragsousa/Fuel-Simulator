package com.example.testmyapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testmyapplication.ui.theme.TestMyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestMyApplicationTheme {
                FuelSimulatorScreen()
            }
        }
    }
}

@Composable
fun FuelSimulatorScreen() {

    var distance by remember { mutableStateOf(value = "") }
    var consumption by remember { mutableStateOf(value = "") }
    var fuelNeeded by remember { mutableDoubleStateOf(value = 0.0) }

    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .padding(all = 24.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.car),
                contentDescription = "Carro",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                stringResource(id = R.string.title),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
            )

            Text(
                stringResource(id = R.string.subtitle),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = distance,
                onValueChange = { distance = it },
                label = { Text(stringResource(R.string.distance)) },
                suffix = { Text("km") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = consumption,
                onValueChange = { consumption = it },
                label = { Text(stringResource(R.string.consumption)) },
                suffix = { Text("km/l") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val distanceValue = distance.toDoubleOrNull() ?: 0.0
                    val consumptionValue = consumption.toDoubleOrNull() ?: 0.0
                    if (consumptionValue > 0) {
                        fuelNeeded = distanceValue / consumptionValue
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.calculate))
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = {}
            ) { Text("Limpar")}

            if (fuelNeeded > 0) {
                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    stringResource(R.string.result),
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "%.1f litros".format(fuelNeeded),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }

    }

}

@Preview
@Composable
private fun FuelSimulatorScreenPreview() {
    TestMyApplicationTheme {
        FuelSimulatorScreen()
    }
}