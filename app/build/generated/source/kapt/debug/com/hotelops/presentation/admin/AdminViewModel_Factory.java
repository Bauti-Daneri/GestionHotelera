package com.hotelops.presentation.admin;

import com.hotelops.domain.usecase.room.AddRoomUseCase;
import com.hotelops.domain.usecase.room.DeleteRoomUseCase;
import com.hotelops.domain.usecase.room.GetRoomsUseCase;
import com.hotelops.domain.usecase.user.CreateUserUseCase;
import com.hotelops.domain.usecase.user.DeleteUserUseCase;
import com.hotelops.domain.usecase.user.GetUsersUseCase;
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

  private final Provider<CreateUserUseCase> createUserUseCaseProvider;

  private final Provider<DeleteUserUseCase> deleteUserUseCaseProvider;

  private final Provider<AddRoomUseCase> addRoomUseCaseProvider;

  private final Provider<DeleteRoomUseCase> deleteRoomUseCaseProvider;

  public AdminViewModel_Factory(Provider<GetUsersUseCase> getUsersUseCaseProvider,
      Provider<GetRoomsUseCase> getRoomsUseCaseProvider,
      Provider<CreateUserUseCase> createUserUseCaseProvider,
      Provider<DeleteUserUseCase> deleteUserUseCaseProvider,
      Provider<AddRoomUseCase> addRoomUseCaseProvider,
      Provider<DeleteRoomUseCase> deleteRoomUseCaseProvider) {
    this.getUsersUseCaseProvider = getUsersUseCaseProvider;
    this.getRoomsUseCaseProvider = getRoomsUseCaseProvider;
    this.createUserUseCaseProvider = createUserUseCaseProvider;
    this.deleteUserUseCaseProvider = deleteUserUseCaseProvider;
    this.addRoomUseCaseProvider = addRoomUseCaseProvider;
    this.deleteRoomUseCaseProvider = deleteRoomUseCaseProvider;
  }

  @Override
  public AdminViewModel get() {
    return newInstance(getUsersUseCaseProvider.get(), getRoomsUseCaseProvider.get(), createUserUseCaseProvider.get(), deleteUserUseCaseProvider.get(), addRoomUseCaseProvider.get(), deleteRoomUseCaseProvider.get());
  }

  public static AdminViewModel_Factory create(Provider<GetUsersUseCase> getUsersUseCaseProvider,
      Provider<GetRoomsUseCase> getRoomsUseCaseProvider,
      Provider<CreateUserUseCase> createUserUseCaseProvider,
      Provider<DeleteUserUseCase> deleteUserUseCaseProvider,
      Provider<AddRoomUseCase> addRoomUseCaseProvider,
      Provider<DeleteRoomUseCase> deleteRoomUseCaseProvider) {
    return new AdminViewModel_Factory(getUsersUseCaseProvider, getRoomsUseCaseProvider, createUserUseCaseProvider, deleteUserUseCaseProvider, addRoomUseCaseProvider, deleteRoomUseCaseProvider);
  }

  public static AdminViewModel newInstance(GetUsersUseCase getUsersUseCase,
      GetRoomsUseCase getRoomsUseCase, CreateUserUseCase createUserUseCase,
      DeleteUserUseCase deleteUserUseCase, AddRoomUseCase addRoomUseCase,
      DeleteRoomUseCase deleteRoomUseCase) {
    return new AdminViewModel(getUsersUseCase, getRoomsUseCase, createUserUseCase, deleteUserUseCase, addRoomUseCase, deleteRoomUseCase);
  }
}
