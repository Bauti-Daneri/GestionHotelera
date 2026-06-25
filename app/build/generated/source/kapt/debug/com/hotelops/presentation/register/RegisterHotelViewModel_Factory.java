package com.hotelops.presentation.register;

import com.hotelops.domain.usecase.auth.RegisterHotelUseCase;
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
public final class RegisterHotelViewModel_Factory implements Factory<RegisterHotelViewModel> {
  private final Provider<RegisterHotelUseCase> registerHotelUseCaseProvider;

  public RegisterHotelViewModel_Factory(
      Provider<RegisterHotelUseCase> registerHotelUseCaseProvider) {
    this.registerHotelUseCaseProvider = registerHotelUseCaseProvider;
  }

  @Override
  public RegisterHotelViewModel get() {
    return newInstance(registerHotelUseCaseProvider.get());
  }

  public static RegisterHotelViewModel_Factory create(
      Provider<RegisterHotelUseCase> registerHotelUseCaseProvider) {
    return new RegisterHotelViewModel_Factory(registerHotelUseCaseProvider);
  }

  public static RegisterHotelViewModel newInstance(RegisterHotelUseCase registerHotelUseCase) {
    return new RegisterHotelViewModel(registerHotelUseCase);
  }
}
