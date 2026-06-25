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
public final class GetOrdersUseCase_Factory implements Factory<GetOrdersUseCase> {
  private final Provider<RoomServiceRepository> roomServiceRepositoryProvider;

  public GetOrdersUseCase_Factory(Provider<RoomServiceRepository> roomServiceRepositoryProvider) {
    this.roomServiceRepositoryProvider = roomServiceRepositoryProvider;
  }

  @Override
  public GetOrdersUseCase get() {
    return newInstance(roomServiceRepositoryProvider.get());
  }

  public static GetOrdersUseCase_Factory create(
      Provider<RoomServiceRepository> roomServiceRepositoryProvider) {
    return new GetOrdersUseCase_Factory(roomServiceRepositoryProvider);
  }

  public static GetOrdersUseCase newInstance(RoomServiceRepository roomServiceRepository) {
    return new GetOrdersUseCase(roomServiceRepository);
  }
}
