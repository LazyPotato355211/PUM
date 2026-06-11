package com.example.puml5z2

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent określa, co zostanie wyświetlone na ekranie
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    // 'rememberSaveable' gwarantuje, że dane przetrwają zmianę orientacji telefonu (obrót ekranu).
    // 'mutableStateOf' sprawia, że każda zmiana tej zmiennej automatycznie odświeży interfejs.
    var num1Str by rememberSaveable { mutableStateOf("") }
    var num2Str by rememberSaveable { mutableStateOf("") }
    var resultText by rememberSaveable { mutableStateOf("result: ...") }

    // sprawdzanie obecnej konfiguracji telefonu
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val calculate = { operation: String ->
        // Próba zamiany tekstu wpisanego przez użytkownika na liczby całkowite (Int).
        // Zwróci 'null', jeśli wpisano głupoty (choć nasza klawiatura na to nie pozwoli).
        val n1 = num1Str.toIntOrNull()
        val n2 = num2Str.toIntOrNull()

        // zabezpieczenie przed brakiem danych
        if (n1 == null || n2 == null) {
            resultText = "result: Wprowadź poprawne liczby całkowite"
        } else {
            // when (odpowiednik switch/case) wykonująca konkretne działanie
            when (operation) {
                "+" -> resultText = "result: ${n1 + n2}"
                "-" -> resultText = "result: ${n1 - n2}"
                "x" -> resultText = "result: ${n1 * n2}"
                "/" -> {
                    // Krytyczne zabezpieczenie przed dzieleniem przez zero!
                    if (n2 == 0) {
                        resultText = "result: Błąd! Dzielenie przez 0"
                    } else {
                        resultText = "result: ${n1 / n2}"
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (isLandscape) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = num1Str,
                    onValueChange = { num1Str = it.filter { char -> char.isDigit() || char == '-' } },
                    label = { Text("enter first number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = num2Str,
                    onValueChange = { num2Str = it.filter { char -> char.isDigit() || char == '-' } },
                    label = { Text("enter second number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
        } else {
            OutlinedTextField(
                value = num1Str,
                onValueChange = { num1Str = it.filter { char -> char.isDigit() || char == '-' } },
                label = { Text("enter first number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp)) // Odstęp między polami

            OutlinedTextField(
                value = num2Str,
                onValueChange = { num2Str = it.filter { char -> char.isDigit() || char == '-' } },
                label = { Text("enter second number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { calculate("+") },
                modifier = Modifier.weight(1f),
                shape = RectangleShape
            ) {
                Text("+", fontSize = 24.sp)
            }
            Button(
                onClick = { calculate("-") },
                modifier = Modifier.weight(1f),
                shape = RectangleShape
            ) {
                Text("-", fontSize = 24.sp)
            }
            Button(
                onClick = { calculate("x") },
                modifier = Modifier.weight(1f),
                shape = RectangleShape
            ) {
                Text("x", fontSize = 24.sp)
            }
            Button(
                onClick = { calculate("/") },
                modifier = Modifier.weight(1f),
                shape = RectangleShape
            ) {
                Text("/", fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = resultText,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary
        )
    }
}