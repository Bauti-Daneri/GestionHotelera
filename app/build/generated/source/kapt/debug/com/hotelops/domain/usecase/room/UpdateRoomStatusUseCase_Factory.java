package com.hotelops.domain.usecase.room;

import com.hotelops.domain.repository.RoomRepository;
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
public final class UpdateRoomStatusUseCase_Factory implements Factory<UpdateRoomStatusUseCase> {
  private final Provider<RoomRepository> roomRepositoryProvider;

  public UpdateRoomStatusUseCase_Factory(Provider<RoomRepository> roomRepositoryProvider) {
    this.roomRepositoryProvider = roomRepositoryProvider;
  }

  @Override
  public UpdateRoomStatusUseCase get() {
    return newInstance(roomRepositoryProvider.get());
  }

  public static UpdateRoomStatusUseCase_Factory create(
      Provider<RoomRepository> roomRepositoryProvider) {
    return new UpdateRoomStatusUseCase_Factory(roomRepositoryProvider);
  }

  public static UpdateRoomStatusUseCase newInstance(RoomRepository roomRepository) {
    return new UpdateRoomStatusUseCase(roomRepository);
  }
}
