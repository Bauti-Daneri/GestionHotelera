package com.hotelops.presentation.admin;

import com.hotelops.domain.usecase.maintenance.DeleteTicketUseCase;
import com.hotelops.domain.usecase.maintenance.GetTicketsUseCase;
import com.hotelops.domain.usecase.room.AddRoomUseCase;
import com.hotelops.domain.usecase.room.DeleteRoomUseCase;
import com.hotelops.domain.usecase.room.GetRoomsUseCase;
import com.hotelops.domain.usecase.room.UpdateRoomStatusUseCase;
import com.hotelops.domain.usecase.roomservice.DeleteOrderUseCase;
import com.hotelops.domain.usecase.roomservice.GetOrdersUseCase;
import com.hotelops.domain.usecase.user.CreateUserUseCase;
import com.hotelops.domain.usecase.user.DeleteUserUseCase;
import com.hotelops.domain.usecase.user.GetUsersUseCase;
import com.hotelops.domain.usecase.user.UpdateUserUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AdminViewModel_Factory implements Factory<AdminViewModel> {
  private final Provider<GetUsersUseCase> getUsersUseCaseProvider;

  private final Provider<GetRoomsUseCase> getRoomsUseCaseProvider;

  private final Provider<GetTicketsUseCase> getTicketsUseCaseProvider;

  private final Provider<GetOrdersUseCase> getOrdersUseCaseProvider;

  private final Provider<CreateUserUseCase> createUserUseCaseProvider;

  private final Provider<DeleteUserUseCase> deleteUserUseCaseProvider;

  private final Provider<AddRoomUseCase> addRoomUseCaseProvider;

  private final Provider<DeleteRoomUseCase> deleteRoomUseCaseProvider;

  private final Provider<UpdateRoomStatusUseCase> updateRoomStatusUseCaseProvider;

  private final Provider<DeleteTicketUseCase> deleteTicketUseCaseProvider;

  private final Provider<DeleteOrderUseCase> deleteOrderUseCaseProvider;

  private final Provider<UpdateUserUseCase> updateUserUseCaseProvider;

  public AdminViewModel_Factory(Provider<GetUsersUseCase> getUsersUseCaseProvider,
      Provider<GetRoomsUseCase> getRoomsUseCaseProvider,
      Provider<GetTicketsUseCase> getTicketsUseCaseProvider,
      Provider<GetOrdersUseCase> getOrdersUseCaseProvider,
      Provider<CreateUserUseCase> createUserUseCaseProvider,
      Provider<DeleteUserUseCase> deleteUserUseCaseProvider,
      Provider<AddRoomUseCase> addRoomUseCaseProvider,
      Provider<DeleteRoomUseCase> deleteRoomUseCaseProvider,
      Provider<UpdateRoomStatusUseCase> updateRoomStatusUseCaseProvider,
      Provider<DeleteTicketUseCase> deleteTicketUseCaseProvider,
      Provider<DeleteOrderUseCase> deleteOrderUseCaseProvider,
      Provider<UpdateUserUseCase> updateUserUseCaseProvider) {
    this.getUsersUseCaseProvider = getUsersUseCaseProvider;
    this.getRoomsUseCaseProvider = getRoomsUseCaseProvider;
    this.getTicketsUseCaseProvider = getTicketsUseCaseProvider;
    this.getOrdersUseCaseProvider = getOrdersUseCaseProvider;
    this.createUserUseCaseProvider = createUserUseCaseProvider;
    this.deleteUserUseCaseProvider = deleteUserUseCaseProvider;
    this.addRoomUseCaseProvider = addRoomUseCaseProvider;
    this.deleteRoomUseCaseProvider = deleteRoomUseCaseProvider;
    this.updateRoomStatusUseCaseProvider = updateRoomStatusUseCaseProvider;
    this.deleteTicketUseCaseProvider = deleteTicketUseCaseProvider;
    this.deleteOrderUseCaseProvider = deleteOrderUseCaseProvider;
    this.updateUserUseCaseProvider = updateUserUseCaseProvider;
  }

  @Override
  public AdminViewModel get() {
    return newInstance(getUsersUseCaseProvider.get(), getRoomsUseCaseProvider.get(), getTicketsUseCaseProvider.get(), getOrdersUseCaseProvider.get(), createUserUseCaseProvider.get(), deleteUserUseCaseProvider.get(), addRoomUseCaseProvider.get(), deleteRoomUseCaseProvider.get(), updateRoomStatusUseCaseProvider.get(), deleteTicketUseCaseProvider.get(), deleteOrderUseCaseProvider.get(), updateUserUseCaseProvider.get());
  }

  public static AdminViewModel_Factory create(Provider<GetUsersUseCase> getUsersUseCaseProvider,
      Provider<GetRoomsUseCase> getRoomsUseCaseProvider,
      Provider<GetTicketsUseCase> getTicketsUseCaseProvider,
      Provider<GetOrdersUseCase> getOrdersUseCaseProvider,
      Provider<CreateUserUseCase> createUserUseCaseProvider,
      Provider<DeleteUserUseCase> deleteUserUseCaseProvider,
      Provider<AddRoomUseCase> addRoomUseCaseProvider,
      Provider<DeleteRoomUseCase> deleteRoomUseCaseProvider,
      Provider<UpdateRoomStatusUseCase> updateRoomStatusUseCaseProvider,
      Provider<DeleteTicketUseCase> deleteTicketUseCaseProvider,
      Provider<DeleteOrderUseCase> deleteOrderUseCaseProvider,
      Provider<UpdateUserUseCase> updateUserUseCaseProvider) {
    return new AdminViewModel_Factory(getUsersUseCaseProvider, getRoomsUseCaseProvider, getTicketsUseCaseProvider, getOrdersUseCaseProvider, createUserUseCaseProvider, deleteUserUseCaseProvider, addRoomUseCaseProvider, deleteRoomUseCaseProvider, updateRoomStatusUseCaseProvider, deleteTicketUseCaseProvider, deleteOrderUseCaseProvider, updateUserUseCaseProvider);
  }

  public static AdminViewModel newInstance(GetUsersUseCase getUsersUseCase,
      GetRoomsUseCase getRoomsUseCase, GetTicketsUseCase getTicketsUseCase,
      GetOrdersUseCase getOrdersUseCase, CreateUserUseCase createUserUseCase,
      DeleteUserUseCase deleteUserUseCase, AddRoomUseCase addRoomUseCase,
      DeleteRoomUseCase deleteRoomUseCase, UpdateRoomStatusUseCase updateRoomStatusUseCase,
      DeleteTicketUseCase deleteTicketUseCase, DeleteOrderUseCase deleteOrderUseCase,
      UpdateUserUseCase updateUserUseCase) {
    return new AdminViewModel(getUsersUseCase, getRoomsUseCase, getTicketsUseCase, getOrdersUseCase, createUserUseCase, deleteUserUseCase, addRoomUseCase, deleteRoomUseCase, updateRoomStatusUseCase, deleteTicketUseCase, deleteOrderUseCase, updateUserUseCase);
  }
}
