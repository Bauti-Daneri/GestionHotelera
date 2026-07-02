package com.hotelops.presentation.maintenance

import android.net.Uri
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
    val filterCategory: TicketCategory? = null,
    val showCreateDialog: Boolean = false,
    val showCamera: Boolean = false,
    val selectedImageUri: Uri? = null,
    val capturedImageUri: Uri? = null,
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
    fun setCategoryFilter(category: TicketCategory?) { _state.value = _state.value.copy(filterCategory = category) }
    fun showCreateDialog() { _state.value = _state.value.copy(showCreateDialog = true, capturedImageUri = null) }
    fun hideDialog() { _state.value = _state.value.copy(showCreateDialog = false) }
    fun showCamera() { _state.value = _state.value.copy(showCamera = true) }
    fun hideCamera() { _state.value = _state.value.copy(showCamera = false) }

    fun showImage(uri: Uri?) { _state.value = _state.value.copy(selectedImageUri = uri) }
    fun hideImage() { _state.value = _state.value.copy(selectedImageUri = null) }

    fun onImageCaptured(uri: Uri) {
        _state.value = _state.value.copy(capturedImageUri = uri, showCamera = false)
    }

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
        val imageUrl = _state.value.capturedImageUri?.toString()
        createTicketUseCase(
            hotelId = hotelId,
            roomId = room.id,
            title = title,
            description = description,
            category = category,
            priority = priority,
            reportedBy = reportedById,
            imageUrl = imageUrl
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
