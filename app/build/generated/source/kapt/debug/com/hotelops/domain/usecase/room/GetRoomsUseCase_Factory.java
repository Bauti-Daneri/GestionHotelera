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
public final class GetRoomsUseCase_Factory implements Factory<GetRoomsUseCase> {
  private final Provider<RoomRepository> roomRepositoryProvider;

  public GetRoomsUseCase_Factory(Provider<RoomRepository> roomRepositoryProvider) {
    this.roomRepositoryProvider = roomRepositoryProvider;
  }

  @Override
  public GetRoomsUseCase get() {
    return newInstance(roomRepositoryProvider.get());
  }

  public static GetRoomsUseCase_Factory create(Provider<RoomRepository> roomRepositoryProvider) {
    return new GetRoomsUseCase_Factory(roomRepositoryProvider);
  }

  public static GetRoomsUseCase newInstance(RoomRepository roomRepository) {
    return new GetRoomsUseCase(roomRepository);
  }
}
