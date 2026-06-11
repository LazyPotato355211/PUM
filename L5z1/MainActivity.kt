package com.example.puml5z1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    // onCreate - wywołuje się w momencie tworzenia ekranu aplikacji przez system
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent określa, co ma zostać narysowane na ekranie (punkt wejścia do UI)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterExample()
                }
            }
        }
    }
}

// @Composable mówi kompilatorowi, że ta funkcja służy do budowy interfejsu (UI)
@Composable
fun CounterExample() {
    // 'rememberSaveable' sprawia, że po obróceniu ekranu (gdy ekran jest rysowany na nowo), wartość nie zresetuje się do 0.
    var counter by rememberSaveable { mutableIntStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center, // Wyśrodkowanie w pionie
        horizontalAlignment = Alignment.CenterHorizontally, // Wyśrodkowanie w poziomie
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(0.3f))

        Text(
            text = counter.toString(),
            fontSize = 250.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                modifier = Modifier.fillMaxWidth(),
                shape = RectangleShape,
                onClick = { counter = 0 }
            ) {
                Text(text = "Reset")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape,
                    onClick = { counter-- }
                ) {
                    Text(text = "Count DOWN")
                }

                Button(
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape,
                    onClick = { counter++ }
                ) {
                    Text(text = "Count UP")
                }
            }
        }
    }
}