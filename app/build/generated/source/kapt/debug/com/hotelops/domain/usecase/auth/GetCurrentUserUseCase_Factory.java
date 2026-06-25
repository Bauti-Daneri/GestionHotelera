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
public final class GetCurrentUserUseCase_Factory implements Factory<GetCurrentUserUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public GetCurrentUserUseCase_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public GetCurrentUserUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static GetCurrentUserUseCase_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new GetCurrentUserUseCase_Factory(authRepositoryProvider);
  }

  public static GetCurrentUserUseCase newInstance(AuthRepository authRepository) {
    return new GetCurrentUserUseCase(authRepository);
  }
}
