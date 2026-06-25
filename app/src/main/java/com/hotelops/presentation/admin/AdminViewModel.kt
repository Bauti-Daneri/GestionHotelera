package com.hotelops.presentation.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hotelops.domain.model.*
import com.hotelops.domain.usecase.room.*
import com.hotelops.domain.usecase.user.*
import com.hotelops.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val addRoomUseCase: AddRoomUseCase,
    private val deleteRoomUseCase: DeleteRoomUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AdminState())
    val state: StateFlow<AdminState> = _state.asStateFlow()

    private var currentHotelId: String? = null

    fun loadData(hotelId: String) {
        currentHotelId = hotelId
        getUsersUseCase(hotelId).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = _state.value.copy(isLoading = true)
                is Resource.Success -> _state.value = _state.value.copy(
                    users = result.data ?: emptyList(),
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

    fun showAddUserDialog() { _state.value = _state.value.copy(showAddUserDialog = true) }
    fun showAddRoomDialog() { _state.value = _state.value.copy(showAddRoomDialog = true) }
    fun hideDialogs() { _state.value = _state.value.copy(showAddUserDialog = false, showAddRoomDialog = false) }

    fun addUser(name: String, email: String, password: String, role: UserRole) {
        val hotelId = currentHotelId ?: return
        createUserUseCase(
            hotelId = hotelId,
            name = name,
            email = email,
            password = password,
            role = role,
            department = role.toDisplayName(),
            phone = null,
            employeeId = "EMP-${System.currentTimeMillis()}"
        ).onEach { result ->
            if (result is Resource.Success) {
                hideDialogs()
            }
        }.launchIn(viewModelScope)
    }

    fun deleteUser(user: User) {
        deleteUserUseCase(user.id).launchIn(viewModelScope)
    }

    fun addRoom(roomNumber: String, floor: Int, type: String) {
        val hotelId = currentHotelId ?: return
        val roomType = runCatching { RoomType.valueOf(type) }.getOrDefault(RoomType.SINGLE)
        addRoomUseCase(hotelId, roomNumber, floor, roomType).onEach { result ->
            if (result is Resource.Success) {
                hideDialogs()
            }
        }.launchIn(viewModelScope)
    }

    fun deleteRoom(room: Room) {
        deleteRoomUseCase(room.id).launchIn(viewModelScope)
    }
}
