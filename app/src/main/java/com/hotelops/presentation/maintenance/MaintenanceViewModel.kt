package com.hotelops.presentation.maintenance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotelops.domain.model.*
import com.hotelops.domain.usecase.maintenance.*
import com.hotelops.domain.usecase.room.GetRoomsUseCase
import com.hotelops.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class MaintenanceState(
    val tickets: List<MaintenanceTicket> = emptyList(),
    val rooms: List<Room> = emptyList(),
    val isLoading: Boolean = false,
    val filterStatus: TicketStatus? = null,
    val showCreateDialog: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class MaintenanceViewModel @Inject constructor(
    private val getTicketsUseCase: GetTicketsUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val createTicketUseCase: CreateTicketUseCase,
    private val updateTicketStatusUseCase: UpdateTicketStatusUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MaintenanceState())
    val state: StateFlow<MaintenanceState> = _state.asStateFlow()

    fun loadData(hotelId: String) {
        getTicketsUseCase(hotelId).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                is Resource.Success -> _state.value = _state.value.copy(
                    tickets = result.data ?: emptyList(),
                    isLoading = false
                )
                is Resource.Error -> _state.value = _state.value.copy(
                    isLoading = false,
                    error = result.message
                )
            }
        }.launchIn(viewModelScope)

        getRoomsUseCase(hotelId).onEach { result ->
            if (result is Resource.Success) {
                _state.value = _state.value.copy(rooms = result.data ?: emptyList())
            }
        }.launchIn(viewModelScope)
    }

    fun setFilter(status: TicketStatus?) { _state.value = _state.value.copy(filterStatus = status) }
    fun showCreateDialog() { _state.value = _state.value.copy(showCreateDialog = true) }
    fun hideDialog() { _state.value = _state.value.copy(showCreateDialog = false) }

    fun createTicket(
        hotelId: String,
        room: Room,
        title: String,
        description: String,
        category: TicketCategory,
        priority: String,
        reportedByName: String,
        reportedById: String
    ) {
        createTicketUseCase(
            hotelId = hotelId,
            roomId = room.id,
            title = title,
            description = description,
            category = category,
            priority = priority,
            reportedBy = reportedById,
            imageUrl = null
        ).onEach { result ->
            if (result is Resource.Success) {
                hideDialog()
            }
        }.launchIn(viewModelScope)
    }

    fun updateStatus(ticketId: String, newStatus: TicketStatus) {
        updateTicketStatusUseCase(ticketId, newStatus, null).launchIn(viewModelScope)
    }
}
