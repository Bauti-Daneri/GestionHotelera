package com.hotelops.domain.usecase.roomservice;

import com.hotelops.domain.repository.RoomServiceRepository;
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
public final class UpdateOrderStatusUseCase_Factory implements Factory<UpdateOrderStatusUseCase> {
  private final Provider<RoomServiceRepository> roomServiceRepositoryProvider;

  public UpdateOrderStatusUseCase_Factory(
      Provider<RoomServiceRepository> roomServiceRepositoryProvider) {
    this.roomServiceRepositoryProvider = roomServiceRepositoryProvider;
  }

  @Override
  public UpdateOrderStatusUseCase get() {
    return newInstance(roomServiceRepositoryProvider.get());
  }

  public static UpdateOrderStatusUseCase_Factory create(
      Provider<RoomServiceRepository> roomServiceRepositoryProvider) {
    return new UpdateOrderStatusUseCase_Factory(roomServiceRepositoryProvider);
  }

  public static UpdateOrderStatusUseCase newInstance(RoomServiceRepository roomServiceRepository) {
    return new UpdateOrderStatusUseCase(roomServiceRepository);
  }
}
