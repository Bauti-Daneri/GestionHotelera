package com.hotelops.presentation.housekeeping;

import com.hotelops.domain.usecase.room.GetRoomsUseCase;
import com.hotelops.domain.usecase.room.UpdateRoomStatusUseCase;
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
public final class HousekeepingViewModel_Factory implements Factory<HousekeepingViewModel> {
  private final Provider<GetRoomsUseCase> getRoomsUseCaseProvider;

  private final Provider<UpdateRoomStatusUseCase> updateRoomStatusUseCaseProvider;

  public HousekeepingViewModel_Factory(Provider<GetRoomsUseCase> getRoomsUseCaseProvider,
      Provider<UpdateRoomStatusUseCase> updateRoomStatusUseCaseProvider) {
    this.getRoomsUseCaseProvider = getRoomsUseCaseProvider;
    this.updateRoomStatusUseCaseProvider = updateRoomStatusUseCaseProvider;
  }

  @Override
  public HousekeepingViewModel get() {
    return newInstance(getRoomsUseCaseProvider.get(), updateRoomStatusUseCaseProvider.get());
  }

  public static HousekeepingViewModel_Factory create(
      Provider<GetRoomsUseCase> getRoomsUseCaseProvider,
      Provider<UpdateRoomStatusUseCase> updateRoomStatusUseCaseProvider) {
    return new HousekeepingViewModel_Factory(getRoomsUseCaseProvider, updateRoomStatusUseCaseProvider);
  }

  public static HousekeepingViewModel newInstance(GetRoomsUseCase getRoomsUseCase,
      UpdateRoomStatusUseCase updateRoomStatusUseCase) {
    return new HousekeepingViewModel(getRoomsUseCase, updateRoomStatusUseCase);
  }
}
