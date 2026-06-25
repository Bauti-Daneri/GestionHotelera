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
public final class DeleteRoomUseCase_Factory implements Factory<DeleteRoomUseCase> {
  private final Provider<RoomRepository> repositoryProvider;

  public DeleteRoomUseCase_Factory(Provider<RoomRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteRoomUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteRoomUseCase_Factory create(Provider<RoomRepository> repositoryProvider) {
    return new DeleteRoomUseCase_Factory(repositoryProvider);
  }

  public static DeleteRoomUseCase newInstance(RoomRepository repository) {
    return new DeleteRoomUseCase(repository);
  }
}
