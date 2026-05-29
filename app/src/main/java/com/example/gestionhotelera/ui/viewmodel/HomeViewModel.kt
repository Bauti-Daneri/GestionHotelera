package com.example.gestionhotelera.ui.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.lifecycle.ViewModel
import com.example.gestionhotelera.domain.model.ModuleInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _modules = MutableStateFlow<List<ModuleInfo>>(emptyList())
    val modules: StateFlow<List<ModuleInfo>> = _modules.asStateFlow()

    init {
        loadModules()
    }

    private fun loadModules() {
        _modules.value = listOf(
            ModuleInfo("Administración", "Reserva de habitaciones, check-in/out e ingresos.", Icons.Rounded.Dashboard),
            ModuleInfo("Limpieza", "Estado y limpieza de habitaciones (Housekeeping).", Icons.Rounded.CleaningServices),
            ModuleInfo("Mantenimiento", "Gestión de averías y tareas técnicas.", Icons.Rounded.Engineering),
            ModuleInfo("Room Service", "Pedidos de comida y servicios al cliente.", Icons.Rounded.Restaurant),
            ModuleInfo("Personal", "Gestión de empleados y turnos de trabajo.", Icons.Rounded.People)
        )
    }
}
