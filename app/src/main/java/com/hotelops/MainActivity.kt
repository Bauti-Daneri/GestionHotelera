package com.hotelops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.hotelops.presentation.navigation.Screen
import com.hotelops.presentation.navigation.HotelOpsNavigation
import com.hotelops.presentation.theme.HotelOpsTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.hotelops.presentation.main.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            !viewModel.state.value.isReady
        }

        enableEdgeToEdge()

        setContent {
            val state by viewModel.state.collectAsState()

            HotelOpsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (state.isReady) {
                        HotelOpsNavigation(
                            startDestination = if (state.currentUser != null)
                                Screen.Main.route else Screen.Login.route
                        )
                    }
                }
            }
        }
    }
}
