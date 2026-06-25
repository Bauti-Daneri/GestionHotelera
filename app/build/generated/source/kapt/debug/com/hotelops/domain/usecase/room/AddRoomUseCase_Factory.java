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
public final class AddRoomUseCase_Factory implements Factory<AddRoomUseCase> {
  private final Provider<RoomRepository> roomRepositoryProvider;

  public AddRoomUseCase_Factory(Provider<RoomRepository> roomRepositoryProvider) {
    this.roomRepositoryProvider = roomRepositoryProvider;
  }

  @Override
  public AddRoomUseCase get() {
    return newInstance(roomRepositoryProvider.get());
  }

  public static AddRoomUseCase_Factory create(Provider<RoomRepository> roomRepositoryProvider) {
    return new AddRoomUseCase_Factory(roomRepositoryProvider);
  }

  public static AddRoomUseCase newInstance(RoomRepository roomRepository) {
    return new AddRoomUseCase(roomRepository);
  }
}
