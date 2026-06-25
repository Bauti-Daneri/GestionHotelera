package com.hotelops.domain.usecase.auth;

import com.hotelops.domain.repository.AuthRepository;
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
public final class LoginUseCase_Factory implements Factory<LoginUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public LoginUseCase_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public LoginUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static LoginUseCase_Factory create(Provider<AuthRepository> authRepositoryProvider) {
    return new LoginUseCase_Factory(authRepositoryProvider);
  }

  public static LoginUseCase newInstance(AuthRepository authRepository) {
    return new LoginUseCase(authRepository);
  }
}
