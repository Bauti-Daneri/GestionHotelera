package com.hotelops.presentation.housekeeping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotelops.domain.model.Room
import com.hotelops.domain.model.RoomStatus
import com.hotelops.domain.usecase.room.GetRoomsUseCase
import com.hotelops.domain.usecase.room.UpdateRoomStatusUseCase
import com.hotelops.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class HousekeepingState(
    val rooms: List<Room> = emptyList(),
    val isLoading: Boolean = false,
    val filterStatus: RoomStatus? = null,
    val error: String? = null
)

@HiltViewModel
class HousekeepingViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase,
    private val updateRoomStatusUseCase: UpdateRoomStatusUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HousekeepingState())
    val state: StateFlow<HousekeepingState> = _state.asStateFlow()

    fun loadRooms(hotelId: String) {
        getRoomsUseCase(hotelId).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                is Resource.Success -> _state.value = _state.value.copy(
                    rooms = result.data ?: emptyList(),
                    isLoading = false
                )
                is Resource.Error -> _state.value = _state.value.copy(
                    isLoading = false,
                    error = result.message
                )
            }
        }.launchIn(viewModelScope)
    }

    fun setFilter(status: RoomStatus?) {
        _state.value = _state.value.copy(filterStatus = status)
    }

    fun updateRoomStatus(roomId: String, newStatus: RoomStatus, cleanedBy: String) {
        updateRoomStatusUseCase(roomId, newStatus, cleanedBy).launchIn(viewModelScope)
    }
}
