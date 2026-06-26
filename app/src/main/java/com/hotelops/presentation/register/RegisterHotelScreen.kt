package com.hotelops.presentation.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.hotelops.presentation.theme.headingColor
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterHotelScreen(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit,
    viewModel: RegisterHotelViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) onRegisterSuccess()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Hotel") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Hotel section
            Text("Datos del Hotel", style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.headingColor)

            OutlinedTextField(
                value = state.hotelName,
                onValueChange = { viewModel.onEvent(RegisterHotelEvent.HotelNameChanged(it)) },
                label = { Text("Nombre del Hotel *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = state.hotelAddress,
                onValueChange = { viewModel.onEvent(RegisterHotelEvent.HotelAddressChanged(it)) },
                label = { Text("Dirección") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = state.hotelCity,
                    onValueChange = { viewModel.onEvent(RegisterHotelEvent.HotelCityChanged(it)) },
                    label = { Text("Ciudad") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = state.hotelCountry,
                    onValueChange = { viewModel.onEvent(RegisterHotelEvent.HotelCountryChanged(it)) },
                    label = { Text("País") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
            OutlinedTextField(
                value = state.hotelPhone,
                onValueChange = { viewModel.onEvent(RegisterHotelEvent.HotelPhoneChanged(it)) },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = state.hotelEmail,
                onValueChange = { viewModel.onEvent(RegisterHotelEvent.HotelEmailChanged(it)) },
                label = { Text("Email del Hotel") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))

            // Admin section
            Text("Cuenta Administrador", style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.headingColor)

            OutlinedTextField(
                value = state.adminName,
                onValueChange = { viewModel.onEvent(RegisterHotelEvent.AdminNameChanged(it)) },
                label = { Text("Nombre del Administrador") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = state.adminEmail,
                onValueChange = { viewModel.onEvent(RegisterHotelEvent.AdminEmailChanged(it)) },
                label = { Text("Email *") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = state.adminPassword,
                onValueChange = { viewModel.onEvent(RegisterHotelEvent.AdminPasswordChanged(it)) },
                label = { Text("Contraseña *") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = state.adminPasswordConfirm,
                onValueChange = { viewModel.onEvent(RegisterHotelEvent.AdminPasswordConfirmChanged(it)) },
                label = { Text("Confirmar Contraseña *") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            if (state.error != null) {
                Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(
                onClick = { viewModel.onEvent(RegisterHotelEvent.Register) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Registrar Hotel")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
