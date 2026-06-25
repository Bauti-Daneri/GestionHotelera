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
public final class DeleteOrderUseCase_Factory implements Factory<DeleteOrderUseCase> {
  private final Provider<RoomServiceRepository> repositoryProvider;

  public DeleteOrderUseCase_Factory(Provider<RoomServiceRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteOrderUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteOrderUseCase_Factory create(
      Provider<RoomServiceRepository> repositoryProvider) {
    return new DeleteOrderUseCase_Factory(repositoryProvider);
  }

  public static DeleteOrderUseCase newInstance(RoomServiceRepository repository) {
    return new DeleteOrderUseCase(repository);
  }
}
