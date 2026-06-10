package com.example.gestionhotelera.ui.admin.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gestionhotelera.domain.model.EmployeeShift
import com.example.gestionhotelera.domain.model.User
import com.example.gestionhotelera.domain.model.UserRole
import com.example.gestionhotelera.domain.model.displayName

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun EmployeeBottomSheet(
    user: User? = null,
    nameError: String? = null,
    emailError: String? = null,
    phoneError: String? = null,
    passwordError: String? = null,
    roleError: String? = null,
    shiftError: String? = null,
    onDismiss: () -> Unit,
    onSave: (String, String, UserRole, String, String, String) -> Unit
) {
    var name by remember { mutableStateOf(user?.name ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf(user?.phone ?: "") }
    var role by remember { mutableStateOf(user?.role ?: UserRole.HOUSEKEEPING) }
    var schedule by remember { mutableStateOf(user?.schedule ?: "") }
    var expanded by remember { mutableStateOf(false) }

    val shifts = EmployeeShift.entries.map { it.displayName }

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(if (user != null) "Editar Usuario" else "Nuevo Usuario", style = MaterialTheme.typography.titleLarge)
            
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = nameError != null,
                supportingText = nameError?.let { { Text(it) } }
            )
            
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError != null,
                supportingText = emailError?.let { { Text(it) } }
            )

            if (user == null) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = passwordError != null,
                    supportingText = passwordError?.let { { Text(it) } }
                )
            }

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = phoneError != null,
                supportingText = phoneError?.let { { Text(it) } }
            )

            Text("Rol", style = MaterialTheme.typography.labelLarge)
            if (roleError != null) Text(roleError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                UserRole.entries.forEach { userRole ->
                    FilterChip(
                        selected = role == userRole,
                        onClick = { role = userRole },
                        label = { Text(userRole.displayName) }
                    )
                }
            }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = schedule,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Horario/Turno") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable).fillMaxWidth(),
                    isError = shiftError != null,
                    supportingText = shiftError?.let { { Text(it) } }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    shifts.forEach { shiftOption ->
                        DropdownMenuItem(
                            text = { Text(shiftOption) },
                            onClick = {
                                schedule = shiftOption
                                expanded = false
                            }
                        )
                    }
                }
            }

            Button(
                onClick = { onSave(name, email, role, schedule, phone, password) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
