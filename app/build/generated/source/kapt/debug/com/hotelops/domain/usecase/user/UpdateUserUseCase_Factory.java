package com.hotelops.domain.usecase.user;

import com.hotelops.domain.repository.UserRepository;
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
public final class UpdateUserUseCase_Factory implements Factory<UpdateUserUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public UpdateUserUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public UpdateUserUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static UpdateUserUseCase_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new UpdateUserUseCase_Factory(userRepositoryProvider);
  }

  public static UpdateUserUseCase newInstance(UserRepository userRepository) {
    return new UpdateUserUseCase(userRepository);
  }
}
