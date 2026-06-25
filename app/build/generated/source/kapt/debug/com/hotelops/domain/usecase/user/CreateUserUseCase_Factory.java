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
public final class CreateUserUseCase_Factory implements Factory<CreateUserUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public CreateUserUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public CreateUserUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static CreateUserUseCase_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new CreateUserUseCase_Factory(userRepositoryProvider);
  }

  public static CreateUserUseCase newInstance(UserRepository userRepository) {
    return new CreateUserUseCase(userRepository);
  }
}
