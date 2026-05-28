package com.example.gestionhotelera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gestionhotelera.ui.theme.GestionHoteleraTheme
import com.example.gestionhotelera.ui.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestionHoteleraTheme {
                HomeScreen()
            }
        }
    }
}
