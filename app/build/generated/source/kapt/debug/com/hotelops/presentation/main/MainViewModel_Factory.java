package com.hotelops.presentation.main;

import com.hotelops.domain.usecase.auth.GetCurrentUserUseCase;
import com.hotelops.domain.usecase.auth.LogoutUseCase;
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
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider;

  private final Provider<LogoutUseCase> logoutUseCaseProvider;

  public MainViewModel_Factory(Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<LogoutUseCase> logoutUseCaseProvider) {
    this.getCurrentUserUseCaseProvider = getCurrentUserUseCaseProvider;
    this.logoutUseCaseProvider = logoutUseCaseProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(getCurrentUserUseCaseProvider.get(), logoutUseCaseProvider.get());
  }

  public static MainViewModel_Factory create(
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<LogoutUseCase> logoutUseCaseProvider) {
    return new MainViewModel_Factory(getCurrentUserUseCaseProvider, logoutUseCaseProvider);
  }

  public static MainViewModel newInstance(GetCurrentUserUseCase getCurrentUserUseCase,
      LogoutUseCase logoutUseCase) {
    return new MainViewModel(getCurrentUserUseCase, logoutUseCase);
  }
}
