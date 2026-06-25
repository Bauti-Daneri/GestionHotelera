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
public final class RegisterHotelUseCase_Factory implements Factory<RegisterHotelUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public RegisterHotelUseCase_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public RegisterHotelUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static RegisterHotelUseCase_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new RegisterHotelUseCase_Factory(authRepositoryProvider);
  }

  public static RegisterHotelUseCase newInstance(AuthRepository authRepository) {
    return new RegisterHotelUseCase(authRepository);
  }
}
