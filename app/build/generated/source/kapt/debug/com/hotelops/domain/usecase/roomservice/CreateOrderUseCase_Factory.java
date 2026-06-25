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
public final class CreateOrderUseCase_Factory implements Factory<CreateOrderUseCase> {
  private final Provider<RoomServiceRepository> roomServiceRepositoryProvider;

  public CreateOrderUseCase_Factory(Provider<RoomServiceRepository> roomServiceRepositoryProvider) {
    this.roomServiceRepositoryProvider = roomServiceRepositoryProvider;
  }

  @Override
  public CreateOrderUseCase get() {
    return newInstance(roomServiceRepositoryProvider.get());
  }

  public static CreateOrderUseCase_Factory create(
      Provider<RoomServiceRepository> roomServiceRepositoryProvider) {
    return new CreateOrderUseCase_Factory(roomServiceRepositoryProvider);
  }

  public static CreateOrderUseCase newInstance(RoomServiceRepository roomServiceRepository) {
    return new CreateOrderUseCase(roomServiceRepository);
  }
}
