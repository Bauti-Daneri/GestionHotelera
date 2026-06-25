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
public final class DeleteUserUseCase_Factory implements Factory<DeleteUserUseCase> {
  private final Provider<UserRepository> userRepositoryProvider;

  public DeleteUserUseCase_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public DeleteUserUseCase get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static DeleteUserUseCase_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new DeleteUserUseCase_Factory(userRepositoryProvider);
  }

  public static DeleteUserUseCase newInstance(UserRepository userRepository) {
    return new DeleteUserUseCase(userRepository);
  }
}
